package io.distributed.unicorn.common.loadbalance;

import io.distributed.unicorn.common.service.IServiceInstance;

public interface ServiceInstanceChooser {
	IServiceInstance choose(String serviceId);
}
