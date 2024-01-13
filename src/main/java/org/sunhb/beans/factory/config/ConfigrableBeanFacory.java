package org.sunhb.beans.factory.config;

import org.sunhb.beans.factory.HierarchicalBeanFactory;

/**
 * @author: SunHB
 * @createTime: 2024/01/05 上午12:26
 * @description: 配置BeanFactory
 */
public interface ConfigrableBeanFacory extends HierarchicalBeanFactory,SingletonBeanRegister {

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
    //销毁单例bean
    void destroySingletons();
}
