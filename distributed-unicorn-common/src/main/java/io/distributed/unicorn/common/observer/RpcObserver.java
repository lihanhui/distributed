package io.distributed.unicorn.common.observer;

public interface RpcObserver {
	void onRpcResponse(Object resp, Throwable t);
	void onRpcRequest(Object req);
}
