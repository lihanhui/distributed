package io.distributed.unicorn.discovery.spring.consul.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import io.distributed.unicorn.discovery.spring.consul.client.UnicornConsulDiscoveryBeanDefinitionRegistrar;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
@Import(UnicornConsulDiscoveryBeanDefinitionRegistrar.class)
@org.springframework.cloud.client.discovery.EnableDiscoveryClient()
public @interface EnableConsulDiscovery {

}
