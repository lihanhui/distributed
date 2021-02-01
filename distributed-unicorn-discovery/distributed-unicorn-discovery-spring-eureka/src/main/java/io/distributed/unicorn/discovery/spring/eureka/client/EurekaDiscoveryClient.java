package io.distributed.unicorn.discovery.spring.eureka.client;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import io.distributed.unicorn.common.circuitbreak.ServiceInstanceStatus;
import io.distributed.unicorn.common.service.IServiceInstance;
import io.distributed.unicorn.common.service.ServiceInstanceBuilder;
import io.distributed.unicorn.discovery.spring.context.client.AbstractDiscoveryClient;
import io.distributed.unicorn.discovery.spring.eureka.annotation.EurekaDiscoveryBean;

// To disable the Eureka Discovery Client, you can set eureka.client.enabled to false. 
// Eureka Discovery Client will also be disabled when spring.cloud.discovery.enabled is set to false.

@EurekaDiscoveryBean
public class EurekaDiscoveryClient extends AbstractDiscoveryClient {
	@Autowired
	private DiscoveryClient discoveryClient;

	public EurekaDiscoveryClient(){
	}
	@Override
	protected DiscoveryClient discoveryClient() {
		return discoveryClient;
	}
}
