package io.distributed.unicorn.coordinator.zookeeper;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.CancelLeadershipException;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

import io.distributed.unicorn.common.coordinator.LeaderStateListener;
import io.distributed.unicorn.common.coordinator.ReadOnlyCoordinatorService;

public class ReadOnlyCoordinatorClient implements ReadOnlyCoordinatorService, Closeable{
	private String zkServers;
	private final CuratorFramework client;
	private List<LeaderStateListener> listeners = new ArrayList<>();
	
	public ReadOnlyCoordinatorClient(String zkServers) {
		super();
		this.zkServers = zkServers;
		this.client = CuratorFrameworkFactory.newClient(
				this.zkServers, 
				new ExponentialBackoffRetry(1000, 3));
	}
	
	@Override
	public void start() {
		client.start();
	}
	
	
	@Override
	public void close() throws IOException {
		for(LeaderStateListener listener: this.listeners) {
			listener.onLeaderLost();
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
