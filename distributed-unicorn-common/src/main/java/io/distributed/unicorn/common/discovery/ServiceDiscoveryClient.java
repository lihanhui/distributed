package io.distributed.unicorn.common.discovery;

import java.util.List;

import io.distributed.unicorn.common.service.IServiceInstance;

public interface ServiceDiscoveryClient{
	/**
	 * A human readable description of the implementation, used in HealthIndicator
	 * @return the description
	 */
	String description();

	/**
	 * Get all ServiceInstances associated with a particular serviceId
	 * @param serviceId the serviceId to query
	 * @return a List of ServiceInstance
	 */
	List<IServiceInstance> getInstances(String serviceId);

	/**
	 * @return all known service ids
	 */
	List<String> getServices();
	
	IServiceInstance getInstance(String serviceId);
}
