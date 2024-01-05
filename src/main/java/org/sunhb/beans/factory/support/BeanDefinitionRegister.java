package org.sunhb.beans.factory.support;


import org.sunhb.beans.BeanException;
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
    /**
     * @Author sunhb
     * @Description 获取beanName对应的BeanDefition
     * @Date 2024/1/4 下午11:46
     * @Param
     * @param beanName
     * @return org.sunhb.beans.factory.config.BeanDefinition
     **/
    BeanDefinition getBeanDefinition(String beanName) throws BeanException;
    /**
     * @Author sunhb
     * @Description 是否包含指定的beanName对应的BeanDefinition
     * @Date 2024/1/4 下午11:45
     * @Param
     * @param beanName
     * @return boolean
     **/
    boolean containsBeanDefiniton(String beanName);

    /**
     * @Author sunhb
     * @Description 获取所有的bean名称
     * @Date 2024/1/4 下午11:47
     * @Param
     * @return java.lang.String[]
     **/
    String[] getBeanDefinitonNames();
}
