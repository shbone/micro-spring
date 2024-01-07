package org.sunhb.beans.factory;

import org.sunhb.beans.BeanException;
import org.sunhb.beans.factory.config.AutowireCapableBeanFactory;
import org.sunhb.beans.factory.config.BeanDefinition;
import org.sunhb.beans.factory.config.BeanPostProcessor;
import org.sunhb.beans.factory.config.ConfigrableBeanFacory;

/**
 * @author: SunHB
 * @createTime: 2024/01/04 下午1:09
 * @description:
 */
public interface ConfigurableListableBeanFactory
        extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigrableBeanFacory {
    /**
     * @Author sunhb
     * @Description 根据beanName查找BeanDefinition
     * @Date 2024/1/4 下午11:31
     * @Param
     * @param beanName
     * @return org.sunhb.beans.factory.config.BeanDefinition
     **/
    BeanDefinition getBeanDefinition(String beanName)throws BeanException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void preInstantiateSingletons()throws BeanException;
}
