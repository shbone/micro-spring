package org.sunhb.beans.factory.support;

import org.sunhb.beans.BeanException;
import org.sunhb.beans.factory.BeanFactory;
import org.sunhb.beans.factory.config.BeanDefinition;

/**
 * @author: SunHB
 * @createTime: 2023/12/23 下午10:09
 * @description:
 */
abstract class AbstractBeanFactory extends DefaultSingletonBeanRegister implements BeanFactory {
    @Override
    public Object getBean(String beanName) throws BeanException {
        Object bean = getSingletonBean(beanName);
        if(bean !=null){
            return bean;
        }
        BeanDefinition beanDefiniton = (BeanDefinition) getBeanDefinition(beanName);
        return createBean(beanName,beanDefiniton);
    }
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefiniton) throws BeanException;
}
