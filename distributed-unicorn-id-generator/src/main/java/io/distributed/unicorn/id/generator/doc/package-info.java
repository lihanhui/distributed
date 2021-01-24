package io.distributed.unicorn.id.generator.doc;

/*
 * UUID:
 * not mono and not index well
 * 
 * MongoDB’s ObjectId:
 *   32bits: a 4-byte epoch timestamp in seconds,
 *   24bits: a 3-byte machine identifier,
 *   16bits: a 2-byte process id, and
 *   24bits: a 3-byte counter, starting with a random value.
 *   32 + 24 + 16 + 24 = 96bits
 * Reference: 
 * 
 * Database Ticket Servers: 
 * This approach uses a centralized database server to generate unique incrementing IDs. It’s like a centralized auto-increment. This approach is used by Flickr.
 * Reference: https://code.flickr.net/2010/02/08/ticket-servers-distributed-unique-primary-keys-on-the-cheap/
 * 
 * Twitter Snowflake:
 * 1: 0 41: timestamp + 5 datacenterId + 5 workerId + 12 sequence
 *   1 + 41 + 5 + 5 + 12 = 64bits
 *   
 * Reference: https://www.callicoder.com/distributed-unique-id-sequence-number-generator/
 */