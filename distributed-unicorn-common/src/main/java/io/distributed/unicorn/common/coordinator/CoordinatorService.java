package io.distributed.unicorn.common.coordinator;

public interface CoordinatorService {
	void addLeaderStateListener(LeaderStateListener listener);
	void start();
	boolean isLeader();
}
