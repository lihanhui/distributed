package io.distributed.unicorn.discovery.spring.nacos.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import io.distributed.unicorn.discovery.spring.nacos.client.UnicornNacosDiscoveryBeanDefinitionRegistrar;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
@Import(UnicornNacosDiscoveryBeanDefinitionRegistrar.class)
@com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery()
public @interface EnableNacosDiscovery {

}
