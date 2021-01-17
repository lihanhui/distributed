package io.distributed.unicorn.discovery.spring.context.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import io.distributed.unicorn.discovery.spring.context.config.ServiceDiscoveryConfiguration;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(ServiceDiscoveryConfiguration.class)
public @interface EnableUnicornDiscovery {

}
