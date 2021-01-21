package io.distributed.unicorn.common.discovery;

import java.util.List;

import io.distributed.unicorn.common.service.IServiceInstance;
import io.distributed.unicorn.common.service.ServiceInstanceChooser;

public abstract class AbstractServiceDiscoveryClient implements ServiceDiscoveryClient {
	private ServiceInstanceChooser chooser ;
	
	public AbstractServiceDiscoveryClient() {
		chooser = new DefaultServiceInstanceChooser();
	}
	public AbstractServiceDiscoveryClient(ServiceInstanceChooser chooser) {
		if(chooser == null) {
			this.chooser = new DefaultServiceInstanceChooser();
		}else {
			this.chooser = chooser;
		}
	}
	public abstract List<IServiceInstance> getInstances(String serviceId);	
	
	public IServiceInstance getInstance(String serviceId){
		return chooser.choose(serviceId);
	}
	
	private class DefaultServiceInstanceChooser implements ServiceInstanceChooser{

		@Override
		public IServiceInstance choose(String serviceId) {
			// TODO Auto-generated method stub
			List<IServiceInstance> instances = AbstractServiceDiscoveryClient.this.getInstances(serviceId);
			if(instances == null || instances.size() == 0) return null;
			//TODO: random load balance
			return null;
		}
		
	}
}
