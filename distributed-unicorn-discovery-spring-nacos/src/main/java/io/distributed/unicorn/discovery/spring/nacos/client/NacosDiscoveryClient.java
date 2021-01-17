package io.distributed.unicorn.discovery.spring.nacos.client;

import io.distributed.unicorn.discovery.spring.context.service.AbstractServiceDiscoveryClient;
import io.distributed.unicorn.discovery.spring.nacos.annotation.NacosDiscoveryBean;

// To disable the Eureka Discovery Client, you can set eureka.client.enabled to false. 
// Eureka Discovery Client will also be disabled when spring.cloud.discovery.enabled is set to false.

@NacosDiscoveryBean
public class NacosDiscoveryClient extends AbstractServiceDiscoveryClient {
	
}
