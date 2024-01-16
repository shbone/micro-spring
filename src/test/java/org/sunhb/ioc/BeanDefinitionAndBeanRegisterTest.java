package org.sunhb.ioc;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.sunhb.beans.BeanException;
import org.sunhb.beans.factory.config.BeanDefinition;
import org.sunhb.beans.factory.support.DefaultListableBeanFactory;
import org.sunhb.service.HelloWorldService;

/**
 * @author: SunHB
 * @createTime: 2023/12/23 下午9:24
 * @description:
 */
public class BeanDefinitionAndBeanRegisterTest {

    @Test
    public void beanDefinitionAndBeanRegisterTest() throws BeanException {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        BeanDefinition helloWorldBeanDefinition = new BeanDefinition(HelloWorldService.class);
        defaultListableBeanFactory.registerBeanDefinition("helloWorldBean",helloWorldBeanDefinition);
        HelloWorldService helloWorldBean = (HelloWorldService)defaultListableBeanFactory.getBean("helloWorldBean");
        Assert.assertEquals(helloWorldBean.hello(),"HelloWorldBean.hello");
        helloWorldBean.hello();
    }

}
