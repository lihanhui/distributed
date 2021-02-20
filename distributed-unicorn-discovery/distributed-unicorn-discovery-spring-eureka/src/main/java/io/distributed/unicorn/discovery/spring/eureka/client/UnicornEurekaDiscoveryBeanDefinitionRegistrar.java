package io.distributed.unicorn.discovery.spring.eureka.client;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class UnicornEurekaDiscoveryBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar{
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		System.out.println("UnicornEurekaDiscoveryBeanDefinitionRegistrar");
		BeanDefinition definition = BeanDefinitionBuilder
				.genericBeanDefinition(UnicornEurekaDiscoveryClient.class)
				.getBeanDefinition();
		registry.registerBeanDefinition(
				"serviceDiscoveryClient",
				definition);
	}

}
