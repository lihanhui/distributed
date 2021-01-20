package io.distributed.unicorn.common.service;

import java.net.URI;
import java.util.Map;

import io.distributed.unicorn.common.observer.RpcObserver;

public abstract class AbstractServiceInstance implements IServiceInstance {
	private ServiceInstanceStat stat;
	private DefaultRpcObserver observer;
	AbstractServiceInstance(){
		stat = new ServiceInstanceStat();
		observer = new DefaultRpcObserver();
	}
	@Override
	public void onRpcResponse(Object resp, Throwable t) {
		observer.onRpcResponse(resp, t);
	}
	@Override
	public void onRpcRequest(Object req) {
		observer.onRpcRequest(req);
	}
	public abstract void serviceId(String serviceId);
	public abstract void host(String host);
	public abstract void port(int port); 
	public abstract void secure(boolean secure);
	public abstract void uri(URI uri);
	public abstract void metadata(Map<String, String> metadata);
	
	public class DefaultRpcObserver implements RpcObserver{
		@Override
		public void onRpcResponse(Object resp, Throwable t) {
			stat.incrTotalResp(1);
			if(t != null) {
				stat.incrTotalError(1);
			}
		}
		@Override
		public void onRpcRequest(Object req) {
			stat.incrTotalReq(1);
		}
	}
}
