package io.distributed.unicorn.lock.lease;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

import org.redisson.api.RLock;

import io.doraemon.distributed.LeaseLockFuture;
import io.doraemon.distributed.lock.AbstractLeaseLock;

public class RedisLeaseLock extends AbstractLeaseLock{
	private RLock lock;
	private static long DEFAULT_LEASE_TIME_SECONDS = 3; //seconds
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
		if(unit.toSeconds(time) < DEFAULT_LEASE_TIME_SECONDS) {
			return lock.tryLock(DEFAULT_LEASE_TIME_SECONDS, TimeUnit.SECONDS);
		}
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
		if(unit.toSeconds(leaseTime) < DEFAULT_LEASE_TIME_SECONDS) {
			lock.lockInterruptibly(DEFAULT_LEASE_TIME_SECONDS, TimeUnit.SECONDS);
		}
		lock.lockInterruptibly(leaseTime, unit);
	}

	public boolean tryLock(long waitTime, long leaseTime, TimeUnit unit) throws InterruptedException {
		if(unit.toSeconds(leaseTime) < DEFAULT_LEASE_TIME_SECONDS) {
			lock.tryLock(waitTime, DEFAULT_LEASE_TIME_SECONDS, TimeUnit.SECONDS);
		}
		return lock.tryLock( waitTime,  leaseTime,  unit);
	}

	public void lock(long leaseTime, TimeUnit unit) {
		if(unit.toSeconds(leaseTime) < DEFAULT_LEASE_TIME_SECONDS) {
			lock.lock(DEFAULT_LEASE_TIME_SECONDS, TimeUnit.SECONDS);
		}
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
	@Override
	public LeaseLockFuture<Boolean> forceUnlockAsync() {
		return new LeaseLockFutureImpl<Boolean>(lock.forceUnlockAsync());
	}
	@Override
	public LeaseLockFuture<Void> unlockAsync() {
		return new LeaseLockFutureImpl<Void>(lock.unlockAsync());
	}
	@Override
	public LeaseLockFuture<Void> unlockAsync(long threadId) {
		return new LeaseLockFutureImpl<Void>(lock.unlockAsync(threadId));
	}
	@Override
	public LeaseLockFuture<Boolean> tryLockAsync() {
		return new LeaseLockFutureImpl<Boolean>(lock.tryLockAsync());
	}
	@Override
	public LeaseLockFuture<Void> lockAsync() {
		return new LeaseLockFutureImpl<Void>(lock.lockAsync());
	}
	@Override
	public LeaseLockFuture<Void> lockAsync(long threadId) {
		return new LeaseLockFutureImpl<Void>(lock.lockAsync(threadId));
	}
	@Override
	public LeaseLockFuture<Void> lockAsync(long leaseTime, TimeUnit unit) {
		if(unit.toSeconds(leaseTime) < DEFAULT_LEASE_TIME_SECONDS) {
			return new LeaseLockFutureImpl<Void>(lock.lockAsync(DEFAULT_LEASE_TIME_SECONDS, TimeUnit.SECONDS));
		}
		return new LeaseLockFutureImpl<Void>(lock.lockAsync(leaseTime, unit));
	}
	@Override
	public LeaseLockFuture<Void> lockAsync(long leaseTime, TimeUnit unit, long threadId) {
		if(unit.toSeconds(leaseTime) < DEFAULT_LEASE_TIME_SECONDS) {
			return new LeaseLockFutureImpl<Void>(lock.lockAsync(DEFAULT_LEASE_TIME_SECONDS, TimeUnit.SECONDS, threadId));
		}
		return new LeaseLockFutureImpl<Void>(lock.lockAsync(leaseTime, unit, threadId));
	}
	@Override
	public LeaseLockFuture<Boolean> tryLockAsync(long threadId) {
		return new LeaseLockFutureImpl<Boolean>(lock.tryLockAsync(threadId));
	}
	@Override
	public LeaseLockFuture<Boolean> tryLockAsync(long waitTime, TimeUnit unit) {
		return new LeaseLockFutureImpl<Boolean>(lock.tryLockAsync(waitTime, unit));
	}
	@Override
	public LeaseLockFuture<Boolean> tryLockAsync(long waitTime, long leaseTime, TimeUnit unit) {
		if(unit.toSeconds(leaseTime) < DEFAULT_LEASE_TIME_SECONDS) {
			return new LeaseLockFutureImpl<Boolean>(lock.tryLockAsync(waitTime, DEFAULT_LEASE_TIME_SECONDS, TimeUnit.SECONDS));
		}
		return new LeaseLockFutureImpl<Boolean>(lock.tryLockAsync(waitTime, leaseTime,unit));
	}
	@Override
	public LeaseLockFuture<Boolean> tryLockAsync(long waitTime, long leaseTime, TimeUnit unit, long threadId) {
		if(unit.toSeconds(leaseTime) < DEFAULT_LEASE_TIME_SECONDS) {
			return new LeaseLockFutureImpl<Boolean>(lock.tryLockAsync(waitTime, DEFAULT_LEASE_TIME_SECONDS, TimeUnit.SECONDS, threadId));
		}
		return new LeaseLockFutureImpl<Boolean>(lock.tryLockAsync(waitTime, leaseTime,unit, threadId));
	}
	@Override
	public LeaseLockFuture<Integer> getHoldCountAsync() {
		return new LeaseLockFutureImpl<Integer>(lock.getHoldCountAsync());
	}
	@Override
	public LeaseLockFuture<Boolean> isLockedAsync() {
		return new LeaseLockFutureImpl<Boolean>(lock.isLockedAsync());
	}
	@Override
	public LeaseLockFuture<Long> remainTimeToLiveAsync() {
		return new LeaseLockFutureImpl<Long>(lock.remainTimeToLiveAsync());
	}
}
