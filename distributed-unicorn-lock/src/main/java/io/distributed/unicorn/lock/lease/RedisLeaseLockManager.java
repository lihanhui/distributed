package io.distributed.unicorn.lock.lease;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import io.distributed.unicorn.common.lock.AbstractLeaseLockManager;
import io.distributed.unicorn.common.lock.LeaseLock;

public class RedisLeaseLockManager extends AbstractLeaseLockManager{
	private RedissonClient redissonClient;
	public RedisLeaseLockManager(RedissonClient redissonClient){
		this.redissonClient = redissonClient;
	}
	// Reference: https://yq.aliyun.com/articles/551640
	public RedisLeaseLockManager(String... clusterNodes){//"redis://127.0.0.1:7000", "redis://127.0.0.1:7001"
		Config config = new Config();
		config.useClusterServers()
		    .setScanInterval(2000) // 集群状态扫描间隔时间，单位是毫秒
		    .addNodeAddress(clusterNodes);//可以用"rediss://"来启用SSL连接
		this.redissonClient = Redisson.create(config);
	}
	public LeaseLock getLeaseLock(String name) {
		return new RedisLeaseLock(redissonClient.getLock(name));
	}
}
