package com.xc.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {
    public static void main(String[] args) {
        //基于注解形式启动
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for(int i=0;i<beanDefinitionNames.length;i++){
            System.out.println(beanDefinitionNames[i]);
        }

//        BeanDefinition
    }
}
