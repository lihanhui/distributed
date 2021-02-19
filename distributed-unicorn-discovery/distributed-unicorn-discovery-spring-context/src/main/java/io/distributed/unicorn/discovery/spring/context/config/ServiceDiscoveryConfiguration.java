package io.distributed.unicorn.discovery.spring.context.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class ServiceDiscoveryConfiguration 
	implements ApplicationContextAware, BeanNameAware, BeanFactoryAware, EnvironmentAware{
	private BeanFactory beanFactory;
	private String name;
	private ApplicationContext context;
	private Environment environment;
	
	public static String UNICORN_DISCOVERY_REGISTRY = "unicorn.discovery.registry";
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
		//System.out.println("BeanFactory beanFactory");
	}
	public void setBeanName(String name) {
		this.name = name;
		System.out.println("String name: " + name);
	}
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
		System.out.println("ApplicationContext applicationContext");
	}
	public void setEnvironment(Environment environment) {
		//System.out.println("Environment environment");
		String registry = environment.getProperty(UNICORN_DISCOVERY_REGISTRY, "nacos");
		//System.out.println(registry);
	    if(registry.equals("nacos")) {
	    	//environment.setProperty("spring.cloud.zookeeper.discovery.enabled", "false");
	    }else if(registry.equals("zookeeper")) {
	    	
	    }else if(registry.equals("eureka")) {
	    	
	    }
	}
}
