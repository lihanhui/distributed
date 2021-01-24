package io.distributed.unicorn.id.generator.snowflake;

public class SnowFlakeConstant {
	public static long workerIdBits = 5L;
	public static long datacenterIdBits = 5L;
	public static long maxWorkerId = -1L ^ (-1L << workerIdBits);
	public static long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
	public static long sequenceBits = 12L;

	public static long workerIdShift = sequenceBits;
	public static long datacenterIdShift  = sequenceBits + workerIdBits;
	public static long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
	public static long sequenceMask = -1L ^ (-1L << sequenceBits);
	
	public static long twepoch = 1288834974657L;
}
