package org.sunhb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sunhb.bean.Person;
import org.sunhb.bean.Plane;
import org.sunhb.beans.BeanException;
import org.sunhb.beans.PropertyValue;
import org.sunhb.beans.PropertyValues;
import org.sunhb.beans.factory.config.BeanDefinition;
import org.sunhb.beans.factory.config.BeanReference;
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

    @Test
    public void testPopulateBeanWithBean() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //注册Plane实例
        PropertyValues propertyValuesForPlane = new PropertyValues();
        propertyValuesForPlane.addPropertyValue(new PropertyValue("nation", "CN"));
        BeanDefinition planeBeanDefinition = new BeanDefinition(Plane.class, propertyValuesForPlane);
        beanFactory.registerBeanDefinition("plane", planeBeanDefinition);

        //注册Person实例
        PropertyValues propertyValuesForPerson = new PropertyValues();
        propertyValuesForPerson.addPropertyValue(new PropertyValue("name", "derek"));
        propertyValuesForPerson.addPropertyValue(new PropertyValue("age", 18));
        //Person实例依赖Plane实例
        propertyValuesForPerson.addPropertyValue(new PropertyValue("plane", new BeanReference("plane")));
        BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValuesForPerson);
        beanFactory.registerBeanDefinition("person", beanDefinition);

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
        Assertions.assertEquals(person.getName(),"derek");
        Assertions.assertEquals(person.getAge(),18);
        Plane plane = person.getPlane();
        Assertions.assertNotNull(plane);
        //assertThat(car).isNotNull();
        Assertions.assertEquals(plane.getNation(),"CN");
        //assertThat(car.getBrand()).isEqualTo("porsche");
    }
}
