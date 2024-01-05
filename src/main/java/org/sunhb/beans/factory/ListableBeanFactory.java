package org.sunhb.beans.factory;

import org.sunhb.beans.BeanException;

import java.util.Map;

/**
 * @author: SunHB
 * @createTime: 2024/01/04 上午12:40
 * @description:
 */
public interface ListableBeanFactory extends BeanFactory{
    /**
     * @Author sunhb
     * @Description 返回指定类型所有实例
     * @Date 2024/1/4 下午1:08
     * @Param
     * @param type
     * @return java.util.Map<java.lang.String,T>
     **/
    <T> Map<String,T> getBeansofType(Class<T> type) throws BeanException;
    /**
     * @Author sunhb
     * @Description
     * @Date 2024/1/4 下午1:08
     * @Param
     * @return java.lang.String[]
     **/
    String[] getBeanDefinitionNames();
}
