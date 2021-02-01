package io.distributed.unicorn.common.coordinator;

public interface LeaderStateListener {
	void onLeaderTaken();
	void onLeaderLost();
}
