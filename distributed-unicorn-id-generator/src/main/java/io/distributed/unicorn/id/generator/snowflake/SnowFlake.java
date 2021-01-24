package io.distributed.unicorn.id.generator.snowflake;

public class SnowFlake {
	private long workerId;
	private long datacenterId;
	private long lastTimestamp = -1L;
	
	public SnowFlake(long workerId, long datacenterId) {
		// sanity check for workerId
		if (workerId > SnowFlakeConstant.maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", SnowFlakeConstant.maxWorkerId));
		}

		if (datacenterId > SnowFlakeConstant.maxDatacenterId || datacenterId < 0) {
			throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", SnowFlakeConstant.maxDatacenterId));
		}
	}
	private long timeGen() {
		return System.currentTimeMillis();
	}
	private long tilNextMillis(Long lastTimestamp ) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	public long nextId() {
		synchronized(this) {
		    long timestamp = timeGen();
		
		    if (timestamp < lastTimestamp) {
		    	throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
		    			lastTimestamp - timestamp));
		    }
		    
		    long sequence = 0;
		    if (lastTimestamp == timestamp) {
		      sequence = (sequence + 1) & SnowFlakeConstant.sequenceMask;
		      if (sequence == 0) {
		        timestamp = tilNextMillis(lastTimestamp);
		      }
		    } 
		
		    lastTimestamp = timestamp;
		    return ((timestamp - SnowFlakeConstant.twepoch) << SnowFlakeConstant.timestampLeftShift) |
		      (datacenterId <<SnowFlakeConstant. datacenterIdShift) |
		      (workerId << SnowFlakeConstant.workerIdShift) | 
		      sequence;
		}
	}

}
