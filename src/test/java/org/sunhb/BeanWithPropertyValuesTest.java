package org.sunhb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sunhb.bean.Person;
import org.sunhb.beans.BeanException;
import org.sunhb.beans.PropertyValue;
import org.sunhb.beans.PropertyValues;
import org.sunhb.beans.factory.config.BeanDefinition;
import org.sunhb.beans.factory.support.DefaultListableBeanFactory;
import org.sunhb.service.HelloWorldService;

/**
 * @author: SunHB
 * @createTime: 2023/12/27 上午12:29
 * @description:
 */
public class BeanWithPropertyValuesTest {

    @Test
    public void testBeanWithPropertyValues() throws BeanException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name","helloWorld"));
        propertyValues.addPropertyValue(new PropertyValue("age",18));
        BeanDefinition beanDefinition = new BeanDefinition(Person.class,propertyValues);
        beanFactory.registerBeanDefinition("person",beanDefinition);
        Person person = (Person)beanFactory.getBean("person");
        Assertions.assertNotNull(person);
        Assertions.assertEquals(person.getAge(),18);
        Assertions.assertEquals(person.getName(),"helloWorld");
    }
}
