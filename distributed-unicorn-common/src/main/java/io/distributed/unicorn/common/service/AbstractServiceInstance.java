package io.distributed.unicorn.common.service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import io.distributed.unicorn.common.circuitbreak.ServiceInstanceStatus;
import io.distributed.unicorn.common.observer.ServiceInstanceObserver;

public abstract class AbstractServiceInstance implements IServiceInstance {
	private String serviceId = "";
	private String instanceId = "";
	private String host = "";
	private int port = 0; 
	private boolean secure = false;
	private URI uri = null;
	private int weight = -1; 
	private Map<String, String> metadata = new HashMap<>();
	private  ServiceInstanceStatus status = ServiceInstanceStatus.HALF_OPEN;
	
	private ServiceInstanceStat stat;
	private ServiceInstanceObserver observer;
	AbstractServiceInstance(){
		stat = new ServiceInstanceStat();
		observer = new DefaultServiceInstanceObserver();
	}
	@Override
	public void onRpcResponse(Object resp, Throwable t) {
		observer.onRpcResponse(resp, t);
	}
	@Override
	public void onRpcRequest(Object req) {
		observer.onRpcRequest(req);
	}
	@Override
	public void onUpdateStatus() {
		observer.onUpdateStatus();
	}
	public void serviceId(String serviceId) {
		this.serviceId =serviceId;
	}
	public void instanceId(String instanceId) {
		this.instanceId =instanceId;
	}
	public void host(String host){
		this.host =host;
	}
	public void port(int port){
		this.port =port;
	} 
	public void secure(boolean secure){
		this.secure =secure;
	}
	public void uri(URI uri){
		this.uri =uri;
	}
	public void weight(int weight){
		this.weight =weight;
	}
	public void metadata(Map<String, String> metadata){
		this.metadata =metadata;
	}
	public void status(ServiceInstanceStatus status) {
		this.status = status;
	}
	
	@Override
	public String serviceId() {
		return serviceId;
	}
	@Override
	public String instanceId() {
		return instanceId;
	}
	@Override
	public String host() {
		return host;
	}
	@Override
	public int port() {
		return port;
	}
	@Override
	public boolean secure() {
		return secure;
	}
	@Override
	public URI uri() {
		return uri;
	}
	@Override
	public int weight() {
		return weight;
	}
	@Override
	public Map<String, String> metadata() {
		return metadata;
	}
	
	@Override
	public ServiceInstanceStatus status() {
		return this.status;
	}
	
	public class DefaultServiceInstanceObserver implements ServiceInstanceObserver{
		@Override
		public void onRpcResponse(Object resp, Throwable t) {
			stat.incrTotalResp(1);
			stat.incrLatestResp(1);
			if(t != null) {
				stat.incrTotalError(1);
				stat.incrLatestError(1);
			}
		}
		@Override
		public void onRpcRequest(Object req) {
			stat.incrTotalReq(1);
			stat.incrLatestReq(1);
		}
		
		@Override
		public void onUpdateStatus() {
			stat.updateStat();
			toUpdateStatus();
		}
		private void toUpdateStatus() {
			// TODO: update status according to stat
			//stat.
		}
	}
}
