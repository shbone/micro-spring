package org.sunhb;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.sunhb.beans.BeanException;
import org.sunhb.beans.factory.BeanFactory;
import org.sunhb.beans.factory.config.BeanDefinition;
import org.sunhb.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author: SunHB
 * @createTime: 2023/12/23 下午9:24
 * @description:
 */
public class TestBeanFactory {
    //@Test
    //public void testBeanFactory(){
    //    BeanFactory beanFactory = new BeanFactory();
    //    beanFactory.registerBean("helloWorld",new HelloWorldBean());
    //    HelloWorldBean  helloWorld = (HelloWorldBean)beanFactory.getBean("helloWorld");
    //    Assert.assertNotNull(helloWorld);
    //    Assert.assertEquals(helloWorld.hello(),"HelloWorldBean.hello");
    //
    //}
    @Test
    public void beanDefinitionAndBeanRegisterTest() throws BeanException {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        BeanDefinition helloWorldBeanDefinition = new BeanDefinition(HelloWorldBean.class);
        defaultListableBeanFactory.registerBeanDefinition("helloWorldBean",helloWorldBeanDefinition);
        HelloWorldBean helloWorldBean = (HelloWorldBean)defaultListableBeanFactory.getBean("helloWorldBean");
        Assert.assertEquals(helloWorldBean.hello(),"HelloWorldBean.hello");
        helloWorldBean.hello();
    }

}
