package org.sunhb;

import org.junit.jupiter.api.Test;
import org.sunhb.bean.Person;
import org.sunhb.beans.factory.support.DefaultListableBeanFactory;
import org.sunhb.beans.factory.xml.XmlBeanDefinitionReader;

import java.io.PrintStream;

/**
 * @author: SunHB
 * @createTime: 2024/01/05 下午4:59
 * @description:
 */
public class XMLFileDefineBeanTest {
    @Test
    public void testXmlFile(){
        //构建beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        Person person = (Person)beanFactory.getBean("person");
        System.out.println(person);
    }
}
