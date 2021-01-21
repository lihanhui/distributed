package io.distributed.unicorn.common.observer;

import io.distributed.unicorn.common.service.ServiceInstanceStatus;

public interface ServiceInstanceObserver {
	void onRpcResponse(Object resp, Throwable t);
	void onRpcRequest(Object req);
	void onInstanceStatus(ServiceInstanceStatus status);
	void onUpdateStat();
}
