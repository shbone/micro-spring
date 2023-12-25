package org.sunhb.beans.factory.support;

import org.sunhb.beans.factory.config.SingletonBeanRegister;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: SunHB
 * @createTime: 2023/12/23 下午10:02
 * @description:
 */
public class DefaultSingletonBeanRegister implements SingletonBeanRegister {
    private Map<String,Object> singletonBeanMap = new HashMap<String, Object>();

    public Object getSingletonBean(String beanName) {
        return singletonBeanMap.get(beanName);
    }

    public void addSingletonBeanMap(String beanName,Object SingletonBean) {
        singletonBeanMap.put(beanName,SingletonBean);
    }

}
