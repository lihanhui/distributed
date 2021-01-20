package io.distributed.unicorn.common.service;

public interface ServiceInstanceChooser {
	IServiceInstance choose(String serviceId);
}
