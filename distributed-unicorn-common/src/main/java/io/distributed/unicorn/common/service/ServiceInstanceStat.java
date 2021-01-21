package io.distributed.unicorn.common.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ServiceInstanceStat {
	private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	private AtomicLong totalReq = new AtomicLong(0);
	private AtomicLong totalResp = new AtomicLong(0);
	private AtomicLong totalError = new AtomicLong(0);
	private AtomicInteger statInterval = new AtomicInteger(0); // seconds
	
	private AtomicLong latestReq = new AtomicLong(0);
	private AtomicLong latestResp = new AtomicLong(0);
	private AtomicLong latestError = new AtomicLong(0);
	
	private long lastUpdateDate = -1;
	
	private IServiceInstance instance;
	private ScheduledFuture<?> result = null;
	ServiceInstanceStat(IServiceInstance instance){
		this.instance = instance;
		result = scheduler.scheduleAtFixedRate(new UpdateStatTask(), 10, 10, TimeUnit.SECONDS);
	}
	private class UpdateStatTask implements Runnable {
		@Override
		public void run() {
			ServiceInstanceStat.this.lastUpdateDate = System.currentTimeMillis();
			ServiceInstanceStat.this.latestError.set(0);
			ServiceInstanceStat.this.latestReq.set(0);
			ServiceInstanceStat.this.latestResp.set(0);
			
			ServiceInstanceStat.this.totalError.set(0);
			ServiceInstanceStat.this.totalReq.set(0);
			ServiceInstanceStat.this.totalResp.set(0);
		}
	}
	public long lastUpdateDate() {
		return lastUpdateDate;
	}
	public long totalReq() {
		return totalReq.get();
	}
	public void incrTotalReq(long incr) {
		this.totalReq.addAndGet(incr);
	}
	public long totalResp() {
		return totalResp.get();
	}
	public void incrTotalResp(long incr) {
		this.totalResp.addAndGet(incr);
	}
	public long totalError() {
		return totalError.get();
	}
	public void incrTotalError(long incr) {
		this.totalError.addAndGet(incr);
	}
	public int statInterval() {
		return statInterval.get();
	}
	public void incrStatInterval(int incr) {
		this.statInterval.addAndGet(incr);
	}
	public long latestReq() {
		return latestReq.get();
	}
	public void incrLatestReq(long incr) {
		this.latestReq.addAndGet(incr);
	}
	public long latestResp() {
		return latestResp.get();
	}
	public void incrLatestResp(long incr) {
		this.latestResp.addAndGet(incr);
	}
	public long latestError() {
		return latestError.get();
	}
	public void incrLatestError(long incr) {
		this.latestError.addAndGet(incr);
	}
}
