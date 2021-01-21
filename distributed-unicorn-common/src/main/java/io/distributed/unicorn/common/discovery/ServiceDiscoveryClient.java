package io.distributed.unicorn.common.discovery;

import io.distributed.unicorn.common.service.IServiceInstance;

public interface ServiceDiscoveryClient{
	IServiceInstance getInstance(String serviceId);
}
