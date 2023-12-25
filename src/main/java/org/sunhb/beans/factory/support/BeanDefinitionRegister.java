package org.sunhb.beans.factory.support;


import org.sunhb.beans.factory.config.BeanDefinition;

/**
 * @author: SunHB
 * @createTime: 2023/12/23 下午9:56
 * @description:
 */
public interface BeanDefinitionRegister {

    /**
     * @Author sunhb
     * @Description 向注册表里注册BeanDefinition
     * @Date 2023/12/23 下午10:00
     * @Param
     * @param beanDefiniton
     * @return void
     **/
    void registerBeanDefinition(String beanName, BeanDefinition beanDefiniton);
}
