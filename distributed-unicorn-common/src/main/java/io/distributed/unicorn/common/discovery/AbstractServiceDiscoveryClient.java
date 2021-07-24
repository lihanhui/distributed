package io.distributed.unicorn.common.discovery;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.distributed.unicorn.common.loadbalance.ServiceInstanceChooser;
import io.distributed.unicorn.common.service.IServiceInstance;
import io.distributed.unicorn.common.service.ServiceInstanceStat;

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
		scheduler.scheduleAtFixedRate(new UpdateStatTask(), ServiceInstanceStat.STAT_INTERVAL, ServiceInstanceStat.STAT_INTERVAL, TimeUnit.SECONDS);
		scheduler.scheduleAtFixedRate(new UpdateCircuitBreakerTask(), ServiceInstanceStat.STAT_INTERVAL, ServiceInstanceStat.STAT_INTERVAL, TimeUnit.SECONDS);
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
			List<IServiceInstance> instances = AbstractServiceDiscoveryClient.this.getInstances(serviceId);
			if(instances == null || instances.size() == 0) return null;
			Random r = new Random();
			return instances.get(r.nextInt(instances.size()));
		}
		
	}
	private class UpdateStatTask implements Runnable {
		@Override
		public void run() {
			List<String> serviceIds = getServices();
			for(String serviceId: serviceIds) {
				List<IServiceInstance> instances = getInstances(serviceId);
				if(instances == null) continue;
				for(IServiceInstance instance: instances) {
					instance.onUpdateStat();
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
				if(instances == null) continue;
				for(IServiceInstance instance: instances) {
					instance.onUpdateStatus();
				}
			}
		}
	}
}
