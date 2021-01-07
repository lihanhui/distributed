package io.distributed.unicorn.lock.lease;

import org.redisson.api.RedissonClient;

import io.doraemon.distributed.lock.AbstractLeaseLockManager;
import io.doraemon.distributed.lock.LeaseLock;

public class RedisLeaseLockManager extends AbstractLeaseLockManager{
	private RedissonClient redissonClient;
	public RedisLeaseLockManager(RedissonClient redissonClient){
		this.redissonClient = redissonClient;
	}
	public LeaseLock getLeaseLock(String name) {
		return new RedisLeaseLock(redissonClient.getLock(name));
	}
}
