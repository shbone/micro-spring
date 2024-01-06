package org.sunhb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sunhb.bean.Person;
import org.sunhb.bean.Plane;
import org.sunhb.beans.factory.support.DefaultListableBeanFactory;
import org.sunhb.beans.factory.xml.XmlBeanDefinitionReader;
import org.sunhb.common.CustomBeanFactoryPostProcessor;
import org.sunhb.common.CustomBeanPostProcessor;

/**
 * @author: SunHB
 * @createTime: 2024/01/06 下午4:00
 * @description:
 */
public class BeanPostProcessorAndBeanFactoryProcessorTest {
    //修改BeanDefinition的属性值
    @Test
    public void testBeanFactoryProcessor(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        CustomBeanFactoryPostProcessor customBeanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
        customBeanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
        Assertions.assertEquals(person.getName(),"DuDan");
    }

    @Test
    public void testBeanProcess(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        //bean实例化之后后处理
        CustomBeanPostProcessor customBeanPostProcessor = new CustomBeanPostProcessor();
        beanFactory.addBeanPostProcessor(customBeanPostProcessor);
        //beanFactory
        Plane  plane = (Plane)beanFactory.getBean("plane");
        System.out.println(plane);
        Assertions.assertEquals(plane.getBrand(),"Rolls-Royce");
        Assertions.assertEquals(plane.getNation(),"China");
    }
}
