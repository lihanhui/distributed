package io.distributed.unicorn.common.service;

import java.net.URI;
import java.util.Map;

public class ServiceInstanceBuilder {
	private SimpleServiceInstance serviceInstance = new SimpleServiceInstance();
	public static ServiceInstanceBuilder builder() {
		return new ServiceInstanceBuilder();
	};
	private IServiceInstance serviceInstance() {
		return serviceInstance;
	}
	public ServiceInstanceBuilder serviceId(String serviceId) {
		serviceInstance.serviceId(serviceId);
		return this;
	}
	public ServiceInstanceBuilder instanceId(String instanceId) {
		serviceInstance.instanceId(instanceId);
		return this;
	}
	public ServiceInstanceBuilder host(String host) {
		serviceInstance.host(host);
		return this;
	}
	public ServiceInstanceBuilder port(int port) {
		serviceInstance.port(port);
		return this;
	}
	public ServiceInstanceBuilder port(boolean secure) {
		serviceInstance.secure(secure);
		return this;
	}
	
	public ServiceInstanceBuilder uri(URI uri) {
		serviceInstance.uri(uri);
		return this;
	}
	public ServiceInstanceBuilder  metadata(Map<String, String> metadata){
		serviceInstance.metadata(metadata);
		return this; 
	}
	public ServiceInstanceBuilder status(ServiceInstanceStatus status) {
		serviceInstance.status(status);
		return this;
	}
	public IServiceInstance build(){
		return serviceInstance();
	}
}
