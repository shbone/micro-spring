package org.sunhb.beans.factory.config;

import org.sunhb.beans.BeanException;

/**
 * @author: SunHB
 * @createTime: 2024/01/06 下午4:09
 * @description:
 */
public interface BeanPostProcessor {
    /**
     * @Author sunhb
     * @Description bean执行初始化前的处理
     * @Date 2024/1/6 下午4:10
     * @Param
     * @param bean
     * @param beanName
     * @return java.lang.Object
     **/
    Object postProcessBeanBeforeInitilization(Object bean,String beanName)throws BeanException;
    /**
     * @Author sunhb
     * @Description bean执行初始化之后的处理
     * @Date 2024/1/6 下午4:23
     * @Param
     * @param bean
     * @param beanName
     * @return java.lang.Object
     **/
    Object postProcessBeanAfterInitilization(Object bean,String beanName) throws BeanException;
}
