package io.distributed.unicorn.common.service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class SimpleServiceInstance extends AbstractServiceInstance{
	private String serviceId = "";
	private String host = "";
	private int port = 0; 
	private boolean secure = false;
	private URI uri = null;
	private Map<String, String> metadata = new HashMap<>();
	private boolean warmup = true;
	
	public void serviceId(String serviceId) {
		this.serviceId =serviceId;
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
	public void metadata(Map<String, String> metadata){
		this.metadata =metadata;
	}
	public void warmup(boolean warmup ) {
		this.warmup = warmup;
	}
	SimpleServiceInstance(){
		
	}
	@Override
	public String serviceId() {
		return serviceId;
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
	public Map<String, String> metadata() {
		return metadata;
	}
	@Override
	public boolean warmup() {
		return warmup;
	}
}
