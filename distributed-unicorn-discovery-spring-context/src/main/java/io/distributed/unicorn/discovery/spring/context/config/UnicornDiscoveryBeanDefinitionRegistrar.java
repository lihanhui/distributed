package io.distributed.unicorn.discovery.spring.context.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class UnicornDiscoveryBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar{
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		// TODO Auto-generated method stub
		BeanDefinition annotationProcessor = BeanDefinitionBuilder
				.genericBeanDefinition(ServiceDiscoveryConfiguration.class)
				.getBeanDefinition();
		registry.registerBeanDefinition(
				ServiceDiscoveryConfiguration.class.getName(),
				annotationProcessor);
	}

}
