package io.distributed.unicorn.common.discovery;

import java.util.List;

import io.distributed.unicorn.common.service.IServiceInstance;
import io.distributed.unicorn.common.service.ServiceInstanceChooser;

public abstract class AbstractServiceDiscoveryClient implements ServiceDiscoveryClient, ServiceInstanceChooser {

	public IServiceInstance choose(String serviceId) {
		return null;
	};
	
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IServiceInstance> getInstances(String serviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getServices() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public IServiceInstance getInstance(String serviceId){
		return choose(serviceId);
	}

}
