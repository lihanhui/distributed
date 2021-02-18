package io.distributed.unicorn.coordinator.zookeeper;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.CancelLeadershipException;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

import io.distributed.unicorn.common.coordinator.CoordinatorService;
import io.distributed.unicorn.common.coordinator.LeaderStateListener;

// http://curator.apache.org/curator-recipes/leader-latch.html
// https://curator.apache.org/curator-recipes/leader-election.html
public class SimpleCoordinatorService extends LeaderSelectorListenerAdapter implements CoordinatorService, Closeable{
	private String zkServers;
	private final CuratorFramework client;
	private final LeaderSelector leaderSelector;
	private final String leaderPath; //"/wolverine/scheduler/leader";
	private List<LeaderStateListener> listeners = new ArrayList<>();
	
	public SimpleCoordinatorService(String zkServers, String path) {
		super();
		this.zkServers = zkServers;
		this.leaderPath = path;
		this.client = CuratorFrameworkFactory.newClient(
				this.zkServers, 
				new ExponentialBackoffRetry(1000, 3));
		// single thread inside - takeLeadership in this thread
		leaderSelector = new LeaderSelector(client, leaderPath, this);
		leaderSelector.autoRequeue();
	}
	
	public void addLeaderStateListener(LeaderStateListener listener) {
		listeners.add(listener);
	}
	@Override
	public void start() {
		client.start();
		leaderSelector.start();
	}
	// called in ConnectionStateManager by another thread instead of thread which will call takeLeadership
	@Override
    public void stateChanged(CuratorFramework client, ConnectionState newState) {
		try{
			super.stateChanged(client, newState);
		}catch(CancelLeadershipException e) {
			hasLeaderShip = false;
			for(LeaderStateListener listener: this.listeners) {
				listener.onLeaderLost();
			}
		}
	}
	
	@Override
	public void close() throws IOException {
		hasLeaderShip = false;
		for(LeaderStateListener listener: this.listeners) {
			listener.onLeaderLost();
		}
	}
	private boolean hasLeaderShip = false;
	@Override
	public void takeLeadership(CuratorFramework arg0) throws Exception {
		hasLeaderShip = true;
		for(LeaderStateListener listener: this.listeners) {
			listener.onLeaderTaken();
		}
		while(hasLeaderShip) {
			try {
				Thread.sleep(3000);
			}catch(Exception e) {
				
			}
		}
	}

	@Override
	public boolean isLeader() {
		return this.hasLeaderShip;
	}

	@Override
	public void write(String path, byte[] data) {
		if(!this.isLeader()) {
			throw new RuntimeException("the current client is not leader");
		}
		try {
			if( null != this.client.checkExists().forPath(path)) {
				this.client.setData().forPath(path, data);
			}else {
				this.client.create().forPath(path, data);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public byte[] read(String path) {
		try {
			if( null != this.client.checkExists().forPath(path)) {
				return this.client.getData().forPath(path);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
