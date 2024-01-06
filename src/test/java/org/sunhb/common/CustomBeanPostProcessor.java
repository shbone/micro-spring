package org.sunhb.common;

import org.sunhb.bean.Plane;
import org.sunhb.beans.BeanException;
import org.sunhb.beans.factory.config.BeanPostProcessor;

/**
 * @author: SunHB
 * @createTime: 2024/01/06 下午4:23
 * @description:
 */
public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeanBeforeInitilization(Object bean, String beanName) throws BeanException {
        System.out.println("CustomBeanPostProcessor.postProcessBeanBeforeInitilization");
        if("plane".equals(beanName)){
            ((Plane)bean).setBrand("Rolls-Royce");
        }
        return bean;
    }

    @Override
    public Object postProcessBeanAfterInitilization(Object bean, String beanName) throws BeanException {
        System.out.println("CustomBeanPostProcessor.postProcessBeanAfterInitilization");
        if("plane".equals(beanName)){
            ((Plane)bean).setNation("China");
        }
        return bean;
    }
}
