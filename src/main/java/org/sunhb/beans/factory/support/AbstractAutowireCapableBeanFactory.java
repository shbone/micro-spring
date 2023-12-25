package org.sunhb.beans.factory.support;

import org.sunhb.beans.BeanException;
import org.sunhb.beans.factory.config.BeanDefinition;

/**
 * @author: SunHB
 * @createTime: 2023/12/23 下午10:21
 * @description:
 */
abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefiniton) throws BeanException {
        return doCreateBean(beanName,beanDefiniton);
    }

    protected  Object doCreateBean(String beanName, BeanDefinition beanDefiniton) throws BeanException {
        Class beanClass = beanDefiniton.getBeanClass();
        Object bean = null;
        try{
            bean = beanClass.newInstance();
        } catch (Exception e){
            throw new BeanException("Instantiation of bean failed",e);
        }
        addSingletonBeanMap(beanName,bean);
        return bean;
    }

}
