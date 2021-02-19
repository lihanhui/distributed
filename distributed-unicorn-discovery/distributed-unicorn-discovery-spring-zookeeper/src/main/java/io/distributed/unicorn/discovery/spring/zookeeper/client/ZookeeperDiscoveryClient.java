package io.distributed.unicorn.discovery.spring.zookeeper.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import io.distributed.unicorn.discovery.spring.context.client.AbstractDiscoveryClient;
import io.distributed.unicorn.discovery.spring.zookeeper.annotation.ZookeeperDiscoveryBean;

// To disable the Eureka Discovery Client, you can set eureka.client.enabled to false. 
// Eureka Discovery Client will also be disabled when spring.cloud.discovery.enabled is set to false.

@ZookeeperDiscoveryBean
public class ZookeeperDiscoveryClient extends AbstractDiscoveryClient {
	@Autowired
	private DiscoveryClient discoveryClient;

	public ZookeeperDiscoveryClient(){
		System.out.println("ZookeeperDiscoveryClient");
	}
	@Override
	protected DiscoveryClient discoveryClient() {
		return discoveryClient;
	}
}
