package io.distributed.unicorn.common.circuitbreak;

public enum ServiceInstanceStatus {// circuit breaker status
	HALF_OPEN(0x0001, "half open"), OPEN(0x0002, "open"), 
	CLOSE(0x0004, "close");
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
