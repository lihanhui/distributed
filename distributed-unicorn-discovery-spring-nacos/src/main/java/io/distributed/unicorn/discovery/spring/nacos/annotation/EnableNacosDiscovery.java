package io.distributed.unicorn.discovery.spring.nacos.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
@ConditionalOnProperty(name="unicorn.discovery.registry", havingValue="nacos")
@com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery()
public @interface EnableNacosDiscovery {

}
