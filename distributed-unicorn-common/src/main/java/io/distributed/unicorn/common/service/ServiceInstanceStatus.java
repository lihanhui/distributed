package io.distributed.unicorn.common.service;

public enum ServiceInstanceStatus {
	INIT(0x0001, "init"), WARM_UP(0x0002, "warm up"), 
	RUNNING(0x0004, "running"), ERROR(0x0008, "error"),
	STOPPED(0x0004, "stopped");
	private int type;
	private String desc;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	private ServiceInstanceStatus(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	public static ServiceInstanceStatus of(int type) {
		for(ServiceInstanceStatus p: ServiceInstanceStatus.values()) {
			if(p.getType() == type) {
				return p;
			}
		}
		return null;
	}
	public static boolean is(ServiceInstanceStatus p, int type) {
		return (p.getType() == type);
	}
}
