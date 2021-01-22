package io.distributed.unicorn.discovery.spring.context.client;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import io.distributed.unicorn.common.circuitbreak.ServiceInstanceStatus;
import io.distributed.unicorn.common.discovery.AbstractServiceDiscoveryClient;
import io.distributed.unicorn.common.service.IServiceInstance;
import io.distributed.unicorn.common.service.ServiceInstanceBuilder;

// To disable the Eureka Discovery Client, you can set eureka.client.enabled to false. 
// Eureka Discovery Client will also be disabled when spring.cloud.discovery.enabled is set to false.

public abstract class AbstractDiscoveryClient extends AbstractServiceDiscoveryClient {
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	private ConcurrentHashMap<String, List<IServiceInstance>> serviceInstanceMap = 
			new ConcurrentHashMap<>();

	public AbstractDiscoveryClient(){
		scheduler.scheduleAtFixedRate(new UpdateServiceInstanceTask(), 10, 10, TimeUnit.SECONDS);
	}
	
	protected abstract DiscoveryClient discoveryClient();
	
	private IServiceInstance build(ServiceInstance instance) {
		ServiceInstanceBuilder b = ServiceInstanceBuilder.builder();
		b.serviceId(instance.getServiceId()).
		instanceId(instance.getInstanceId()).
		host(instance.getHost()).
		port(instance.getPort()).
		metadata(instance.getMetadata()).status(ServiceInstanceStatus.HALF_OPEN);
		return b.build();
	}
	private void updateServiceInstances(String service, List<ServiceInstance> instances) {
		List<IServiceInstance> preInstances = this.serviceInstanceMap.get(service);
		if(preInstances == null) preInstances = new LinkedList<>();
		
		HashMap<String, IServiceInstance> preInstanceMap = new HashMap<>();
		preInstances.forEach( instance -> {
			preInstanceMap.put(instance.instanceId(), instance);
		});
		
		List<IServiceInstance> newInstances = new LinkedList<>();
		for(ServiceInstance instance: instances) {
			if(!preInstanceMap.containsKey(instance.getInstanceId())) {
				newInstances.add(build(instance));
			}else {
				newInstances.add(preInstanceMap.remove(instance.getInstanceId()));
			}
		}
	
		serviceInstanceMap.put(service, newInstances);
	}
	private class UpdateServiceInstanceTask implements Runnable{
		@Override
		public void run() {
			
			List<String> services = discoveryClient().getServices();
			
			for(String service: services) {
				updateServiceInstances(service, discoveryClient().getInstances(service));
			}
			
		}
	}
	@Override
	public List<IServiceInstance> getInstances(String serviceId) {
		return serviceInstanceMap.get(serviceId);
	}
	@Override
	public List<String> getServices() {
		List<String> serviceIds = new LinkedList<>();
		for (String serviceId: serviceInstanceMap.keySet()) {
			serviceIds.add(serviceId);
		}
		return serviceIds;
	}
	
}
