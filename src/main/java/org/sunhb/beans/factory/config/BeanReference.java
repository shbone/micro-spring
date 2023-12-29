package org.sunhb.beans.factory.config;

/**
 * @author: SunHB
 * @createTime: 2023/12/28 下午8:54
 * @description: 一个bean对另一个bean的引用
 */
public class BeanReference {
    private String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
