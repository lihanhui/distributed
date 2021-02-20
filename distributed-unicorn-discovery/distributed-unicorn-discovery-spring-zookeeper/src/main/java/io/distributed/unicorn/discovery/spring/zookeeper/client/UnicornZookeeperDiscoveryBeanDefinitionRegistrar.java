package io.distributed.unicorn.discovery.spring.zookeeper.client;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class UnicornZookeeperDiscoveryBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar{
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		System.out.println("UnicornZookeeperDiscoveryBeanDefinitionRegistrar");
		BeanDefinition definition = BeanDefinitionBuilder
				.genericBeanDefinition(UnicornZookeeperDiscoveryClient.class)
				.getBeanDefinition();
		registry.registerBeanDefinition(
				"serviceDiscoveryClient",
				definition);
	}

}
