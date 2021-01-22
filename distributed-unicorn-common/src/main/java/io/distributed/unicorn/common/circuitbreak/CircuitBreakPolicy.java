package io.distributed.unicorn.common.circuitbreak;

public enum CircuitBreakPolicy {
	ROUND_ROBIN(0x0001, "round_robin"), LEAST_ACTIVE(0x0002, "least_active"), 
	RANDOM(0x0004, "random");
	private int type;
	private String policy;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPolicy() {
		return policy;
	}
	public void setPolicy(String policy) {
		this.policy = policy;
	}
	private CircuitBreakPolicy(int type, String policy) {
		this.type = type;
		this.policy = policy;
	}
	public static CircuitBreakPolicy of(String policy) {
		for(CircuitBreakPolicy p: CircuitBreakPolicy.values()) {
			if(p.getPolicy().equals(policy)) {
				return p;
			}
		}
		return null;
	}
	public static CircuitBreakPolicy of(int type) {
		for(CircuitBreakPolicy p: CircuitBreakPolicy.values()) {
			if(p.getType() == type) {
				return p;
			}
		}
		return null;
	}
	public static boolean is(CircuitBreakPolicy p, String policy) {
		return (p.getPolicy().equals(policy)); 
	}
	public static boolean is(CircuitBreakPolicy p, int type) {
		return (p.getType() == type);
	}
}
