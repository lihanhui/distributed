package io.distributed.unicorn.common.coordinator;

public interface ReadOnlyCoordinatorService {
	void start();
	byte[] read(String path);
}
