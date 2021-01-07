package io.distributed.unicorn.lock.lease;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

import org.redisson.api.RLock;

import io.doraemon.distributed.lock.AbstractLeaseLock;

public class RedisLeaseLock extends AbstractLeaseLock{
	private RLock lock;
	public RedisLeaseLock(RLock lock) {
		this.lock = lock;
	}
	public void lock() {
		lock.lock();
	}

	public void lockInterruptibly() throws InterruptedException {
		lock.lockInterruptibly();
	}

	public boolean tryLock() {
		return lock.tryLock();
	}

	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return lock.tryLock(time, unit);
	}

	public void unlock() {
		lock.unlock();
	}

	public Condition newCondition() {
		return lock.newCondition();
	}

	public String getName() {
		return lock.getName();
	}

	public void lockInterruptibly(long leaseTime, TimeUnit unit) throws InterruptedException {
		lock.lockInterruptibly(leaseTime, unit);
	}

	public boolean tryLock(long waitTime, long leaseTime, TimeUnit unit) throws InterruptedException {
		return lock.tryLock( waitTime,  leaseTime,  unit);
	}

	public void lock(long leaseTime, TimeUnit unit) {
		lock.lock(leaseTime, unit);
	}

	public boolean forceUnlock() {
		return lock.forceUnlock();
	}

	public boolean isLocked() {
		return lock.isLocked();
	}

	public boolean isHeldByThread(long threadId) {
		return lock.isHeldByThread(threadId);
	}

	public boolean isHeldByCurrentThread() {
		return lock.isHeldByCurrentThread();
	}

	public int getHoldCount() {
		return lock.getHoldCount();
	}

	public long remainTimeToLive() {
		return lock.remainTimeToLive();
	}

}
