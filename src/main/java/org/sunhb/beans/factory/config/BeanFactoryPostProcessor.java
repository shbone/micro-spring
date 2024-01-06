package org.sunhb.beans.factory.config;

import org.sunhb.beans.BeanException;
import org.sunhb.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author: SunHB
 * @createTime: 2024/01/06 下午3:50
 * @description: 允许自定义修改BeanDefinition
 */
public interface BeanFactoryPostProcessor {
    /**
     * @Author sunhb
     * @Description 在所有BeanDefinition定义完之后，在bean实例化之前，负责修改BeanDefiniton的属性值
     * @Date 2024/1/6 下午3:53
     * @Param 
     * @param beanFactory
     * @return void
     **/
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)throws BeanException;
}
