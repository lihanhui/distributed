package io.distributed.unicorn.discovery.spring.zookeeper.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Import;

import io.distributed.unicorn.discovery.spring.zookeeper.client.UnicornZookeeperDiscoveryBeanDefinitionRegistrar;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@ConditionalOnProperty(name="unicorn.discovery.registry", havingValue="zookeeper")
@Import(UnicornZookeeperDiscoveryBeanDefinitionRegistrar.class)
@org.springframework.cloud.client.discovery.EnableDiscoveryClient()
public @interface EnableZookeeperDiscovery {

}
