package io.distributed.unicorn.discovery.spring.nacos.client;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.pojo.ListView;

import io.distributed.unicorn.common.discovery.AbstractServiceDiscoveryClient;
import io.distributed.unicorn.common.service.IServiceInstance;
import io.distributed.unicorn.common.service.ServiceInstanceBuilder;
import io.distributed.unicorn.common.service.ServiceInstanceStatus;
import io.distributed.unicorn.discovery.spring.nacos.annotation.NacosDiscoveryBean;

@NacosDiscoveryBean
public class NacosDiscoveryClient extends AbstractServiceDiscoveryClient {
	@NacosInjected
	private NamingService namingService;
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	private ConcurrentHashMap<String, List<IServiceInstance>> serviceInstanceMap = 
			new ConcurrentHashMap<>();

	public NacosDiscoveryClient(){
		scheduler.scheduleAtFixedRate(new UpdateServiceInstanceTask(), 10, 10, TimeUnit.SECONDS);
	}
	private IServiceInstance build(Instance instance) {
		ServiceInstanceBuilder b = ServiceInstanceBuilder.builder();
		b.serviceId(instance.getServiceName()).
		instanceId(instance.getInstanceId()).
		host(instance.getIp()).
		port(instance.getPort()).
		metadata(instance.getMetadata()).status(ServiceInstanceStatus.WARM_UP);
		return b.build();
	}
	private void updateServiceInstances(String service, List<Instance> instances) {
		List<IServiceInstance> preInstances = this.serviceInstanceMap.get(service);
		if(preInstances == null) preInstances = new LinkedList<>();
		
		HashMap<String, IServiceInstance> preInstanceMap = new HashMap<>();
		preInstances.forEach( instance -> {
			preInstanceMap.put(instance.instanceId(), instance);
		});
		
		List<IServiceInstance> newInstances = new LinkedList<>();
		for(Instance instance: instances) {
			if(!preInstanceMap.containsKey(instance.getInstanceId())) {
				newInstances.add(build(instance));
			}else {
				newInstances.add(preInstanceMap.remove(instance.getInstanceId()));
			}
		}
		preInstanceMap.values().forEach( instance->{
			instance.onInstanceStatus(ServiceInstanceStatus.STOPPED);
		});
		serviceInstanceMap.put(service, newInstances);
	}
	private class UpdateServiceInstanceTask implements Runnable{

		@Override
		public void run() {
			int pageNumber = 0;
			int pageSize = 20;
			
			try {
				ListView<String> services = 
						namingService.getServicesOfServer(pageNumber, pageSize);
				do {
					for(String service: services.getData()) {
						updateServiceInstances(service, namingService.getAllInstances(service));
					}
					++pageNumber;
				}while(services.getCount() == pageSize);
			} catch (NacosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
