package org.sunhb.beans.factory.support;

import org.sunhb.beans.BeanException;
import org.sunhb.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author: SunHB
 * @createTime: 2023/12/25 下午10:34
 * @description:
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy{
    /**
     * @Author sunhb
     * @Description 根据Bean实例化策略，无参构造Bean实例
     * @Date 2023/12/26 上午9:03
     * @Param
     * @param beanDefinition
     * @return java.lang.Object
     **/
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeanException {
        //return null;
        Class beanClass = beanDefinition.getBeanClass();
        try {
            Constructor constructor = beanClass.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            throw new BeanException("Fail to instantiate [+"+beanClass.getName()+"]",e);
        }
    }
}
