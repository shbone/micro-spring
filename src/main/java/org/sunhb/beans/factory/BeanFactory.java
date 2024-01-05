package org.sunhb.beans.factory;

import org.sunhb.beans.BeanException;

/**
 * @author: SunHB
 * @createTime: 2023/12/23 下午9:50
 * @description:
 */
public interface BeanFactory {
    /**
     * @Author sunhb
     * @Description Bean工厂构造器
     * @Date 2023/12/23 下午9:52
     * @Param
     * @param name
     * @return java.lang.Object
     **/
    Object getBean(String name) throws BeanException;

    /**
     * @Author sunhb
     * @Description 根据名称和类型找bean
     * @Date 2024/1/5 上午12:09
     * @Param
     * @param name
     * @param reuqiredType
     * @return T
     **/
    <T> T getBean(String name,Class<T> reuqiredType) throws BeanException;
}
