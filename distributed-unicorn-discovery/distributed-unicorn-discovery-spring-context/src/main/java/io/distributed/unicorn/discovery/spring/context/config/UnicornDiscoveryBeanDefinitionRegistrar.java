package io.distributed.unicorn.discovery.spring.context.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class UnicornDiscoveryBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar{
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		System.out.println("UnicornDiscoveryBeanDefinitionRegistrar");
		BeanDefinition annotationProcessor = BeanDefinitionBuilder
				.genericBeanDefinition(ServiceDiscoveryConfiguration.class)
				.getBeanDefinition();
		String beanName = ServiceDiscoveryConfiguration.class.getName();
		beanName = beanName.substring(beanName.lastIndexOf(".")+1);
		registry.registerBeanDefinition(
				beanName,
				annotationProcessor);
	}

}
