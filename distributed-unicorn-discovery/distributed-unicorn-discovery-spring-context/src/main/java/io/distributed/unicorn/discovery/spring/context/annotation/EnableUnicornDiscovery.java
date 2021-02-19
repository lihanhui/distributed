package io.distributed.unicorn.discovery.spring.context.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import io.distributed.unicorn.discovery.spring.context.config.UnicornDiscoveryBeanDefinitionRegistrar;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(UnicornDiscoveryBeanDefinitionRegistrar.class)
public @interface EnableUnicornDiscovery {

}
