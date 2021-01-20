package io.distributed.unicorn.common.service;

import java.net.URI;
import java.util.Map;

import io.distributed.unicorn.common.observer.RpcObserver;

public interface IServiceInstance extends RpcObserver{
		/**
		 * @return the service id as registered.
		 */
		String serviceId();

		/**
		 * @return the hostname of the registered ServiceInstance
		 */
		String host();

		/**
		 * @return the port of the registered ServiceInstance
		 */
		int port();

		/**
		 * @return if the port of the registered ServiceInstance is https or not
		 */
		boolean secure();

		/**
		 * @return the service uri address
		 */
		URI uri();

		/**
		 * @return the key value pair metadata associated with the service instance
		 */
		Map<String, String> metadata();
		
		
		boolean warmup();
}
