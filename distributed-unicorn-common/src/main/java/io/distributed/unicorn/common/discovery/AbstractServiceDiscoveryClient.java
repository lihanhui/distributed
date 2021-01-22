package io.distributed.unicorn.common.discovery;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.distributed.unicorn.common.loadbalance.ServiceInstanceChooser;
import io.distributed.unicorn.common.service.IServiceInstance;

public abstract class AbstractServiceDiscoveryClient implements ServiceDiscoveryClient {
	private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private ServiceInstanceChooser chooser ;
	
	public AbstractServiceDiscoveryClient() {
		this(null);
	}
	public AbstractServiceDiscoveryClient(ServiceInstanceChooser chooser) {
		if(chooser == null) {
			this.chooser = new DefaultServiceInstanceChooser();
		}else {
			this.chooser = chooser;
		}
		scheduler.scheduleAtFixedRate(new UpdateStatTask(), 10, 10, TimeUnit.SECONDS);
		scheduler.scheduleAtFixedRate(new UpdateCircuitBreakerTask(), 100, 100, TimeUnit.MILLISECONDS);
	}
	//
	public abstract List<IServiceInstance> getInstances(String serviceId);	
	public abstract List<String> getServices();
	
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
	private class UpdateStatTask implements Runnable {
		@Override
		public void run() {
			List<String> serviceIds = getServices();
			for(String serviceId: serviceIds) {
				List<IServiceInstance> instances = getInstances(serviceId);
				if(instances != null) continue;
				for(IServiceInstance instance:instances) {
					instance.onUpdateStatus();
				}
			}
		}
	}
	private class UpdateCircuitBreakerTask implements Runnable {
		@Override
		public void run() {
			List<String> serviceIds = getServices();
			for(String serviceId: serviceIds) {
				List<IServiceInstance> instances = getInstances(serviceId);
				if(instances != null) continue;
				for(IServiceInstance instance:instances) {
					//TODO: 
					//instance.s
				}
			}
		}
	}
}
