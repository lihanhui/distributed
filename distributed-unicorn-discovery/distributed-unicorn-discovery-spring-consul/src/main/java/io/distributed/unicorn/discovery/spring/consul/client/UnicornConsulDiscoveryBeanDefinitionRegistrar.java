package io.distributed.unicorn.discovery.spring.consul.client;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class UnicornConsulDiscoveryBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar{
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		System.out.println("UnicornConsulDiscoveryBeanDefinitionRegistrar");
		BeanDefinition definition = BeanDefinitionBuilder
				.genericBeanDefinition(UnicornConsulDiscoveryClient.class)
				.getBeanDefinition();
		registry.registerBeanDefinition(
				"serviceDiscoveryClient",
				definition);
	}

}
