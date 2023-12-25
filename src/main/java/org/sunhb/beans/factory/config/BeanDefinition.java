package org.sunhb.beans.factory.config;

/**
 * @author: SunHB
 * @createTime: 2023/12/23 下午9:58
 * @description:
 */
public class BeanDefinition {
    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }
}
