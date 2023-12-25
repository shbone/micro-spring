package org.sunhb.beans.factory.config;

/**
 * @author: SunHB
 * @createTime: 2023/12/23 下午9:53
 * @description:
 */
public interface SingletonBeanRegister {

    Object getSingletonBean(String name);
}
