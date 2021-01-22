package io.distributed.unicorn.common.observer;

public interface ServiceInstanceObserver {
	void onRpcResponse(Object resp, Throwable t);
	void onRpcRequest(Object req);
	void onUpdateStatus();
}
