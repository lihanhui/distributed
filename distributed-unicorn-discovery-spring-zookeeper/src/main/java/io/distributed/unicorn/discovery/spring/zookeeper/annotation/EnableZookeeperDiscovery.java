package io.distributed.unicorn.discovery.spring.zookeeper.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
@ConditionalOnProperty(name="unicorn.discovery.registry", havingValue="zookeeper")
@org.springframework.cloud.client.discovery.EnableDiscoveryClient()
public @interface EnableZookeeperDiscovery {

}
