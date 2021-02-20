package io.distributed.unicorn.discovery.spring.nacos.client;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class UnicornNacosDiscoveryBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar{
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		System.out.println("UnicornNacosDiscoveryBeanDefinitionRegistrar");
		BeanDefinition definition = BeanDefinitionBuilder
				.genericBeanDefinition(UnicornNacosDiscoveryClient.class)
				.getBeanDefinition();
		registry.registerBeanDefinition(
				"serviceDiscoveryClient",
				definition);
	}

}
