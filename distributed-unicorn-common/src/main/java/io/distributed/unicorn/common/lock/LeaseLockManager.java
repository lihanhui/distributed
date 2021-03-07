package io.distributed.unicorn.common.lock;

public interface LeaseLockManager {
	public LeaseLock getLeaseLock(String name);
}
