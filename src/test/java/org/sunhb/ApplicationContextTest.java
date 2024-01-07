package org.sunhb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sunhb.bean.Person;
import org.sunhb.bean.Plane;
import org.sunhb.beans.context.support.ClasspathXmlApplicationContext;
import org.sunhb.beans.factory.support.DefaultListableBeanFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author: SunHB
 * @createTime: 2024/01/07 下午4:42
 * @description:
 */
public class ApplicationContextTest {

    @Test
    public void testClassPathXmlApplicationContext(){
        String configLocation = "classpath:spring.xml";
        ClasspathXmlApplicationContext applicationContext = new ClasspathXmlApplicationContext(configLocation);

        DefaultListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        Plane plane = beanFactory.getBean("plane", Plane.class);
        System.out.println(plane);
        //plane brand属性修改
        Assertions.assertEquals(plane.getBrand(),"Rolls-Royce");

        Person person = applicationContext.getBean("person", Person.class);
        assertThat(person.getName()).isEqualTo("DuDan");
    }
}
