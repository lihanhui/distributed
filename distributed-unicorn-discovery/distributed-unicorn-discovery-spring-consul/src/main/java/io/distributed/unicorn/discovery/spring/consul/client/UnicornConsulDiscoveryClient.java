package io.distributed.unicorn.discovery.spring.consul.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import io.distributed.unicorn.discovery.spring.context.client.AbstractDiscoveryClient;

// To disable the Eureka Discovery Client, you can set eureka.client.enabled to false. 
// Eureka Discovery Client will also be disabled when spring.cloud.discovery.enabled is set to false.

public class UnicornConsulDiscoveryClient extends AbstractDiscoveryClient {
	@Autowired
	private DiscoveryClient discoveryClient;

	public UnicornConsulDiscoveryClient(){
	}
	@Override
	protected DiscoveryClient discoveryClient() {
		return discoveryClient;
	}
}
