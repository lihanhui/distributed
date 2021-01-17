package io.distributed.unicorn.discovery.spring.eureka.client;

import io.distributed.unicorn.discovery.spring.context.service.AbstractServiceDiscoveryClient;
import io.distributed.unicorn.discovery.spring.eureka.annotation.EurekaDiscoveryBean;

// To disable the Eureka Discovery Client, you can set eureka.client.enabled to false. 
// Eureka Discovery Client will also be disabled when spring.cloud.discovery.enabled is set to false.

@EurekaDiscoveryBean
public class EurekaDiscoveryClient extends AbstractServiceDiscoveryClient {
	
}
