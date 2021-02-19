package io.distributed.unicorn.discovery.spring.zookeeper.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.distributed.unicorn.discovery.spring.context.annotation.DiscoveryBean;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@EnableZookeeperDiscovery
@DiscoveryBean
public @interface ZookeeperDiscoveryBean {
	
}
