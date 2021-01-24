package io.distributed.unicorn.common.service;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ServiceInstanceStat {
	private AtomicLong totalReq = new AtomicLong(0);
	private AtomicLong totalResp = new AtomicLong(0);
	private AtomicLong totalError = new AtomicLong(0);
	
	private AtomicLong latestReq = new AtomicLong(0);
	private AtomicLong latestResp = new AtomicLong(0);
	private AtomicLong latestError = new AtomicLong(0);
	
	private long continuousErrorCount = 0; //连续统计周期发生错误（比例大于50%）的次数
	private long continuousSuccessCount = 0; //连续统计周期发生错误比例小于50%的次数
	private long lastUpdateDate = -1;
	public static final long STAT_INTERVAL = 1; // seconds
	private final long createDate = System.currentTimeMillis();
	private UpdateStatCommand<String> updateStatCommand = new UpdateStatCommand<>();
	
	ServiceInstanceStat(){
		
	}
	public long continuousErrorCount() {
		return continuousErrorCount;
	}
	public long continuousSuccessCount(){
		return continuousSuccessCount;
	}
	public long createDate() {
		return createDate;
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
	public long statInterval() {
		return STAT_INTERVAL;
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
	public void updateStat() {
		try {
			updateStatCommand.call();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private class UpdateStatCommand<String> implements Callable<String>{
		@Override
		public String call() throws Exception {
			ServiceInstanceStat.this.lastUpdateDate = System.currentTimeMillis();
			long error = ServiceInstanceStat.this.latestError.get();
			if(error > 0 && (100 * error) / ServiceInstanceStat.this.latestReq() > 50) {//有错且错误大于50%
				++ServiceInstanceStat.this.continuousErrorCount;
				continuousSuccessCount = 0;
			}else {// 重新计数
				ServiceInstanceStat.this.continuousErrorCount = 0;
				++continuousSuccessCount;
			}
			ServiceInstanceStat.this.latestError.set(0);
			ServiceInstanceStat.this.latestResp.set(0);
			ServiceInstanceStat.this.latestReq.set(0);
			ServiceInstanceStat.this.totalError.set(0);
			ServiceInstanceStat.this.totalResp.set(0);
			ServiceInstanceStat.this.totalReq.set(0);
			return null;
		}
		
	}
}
