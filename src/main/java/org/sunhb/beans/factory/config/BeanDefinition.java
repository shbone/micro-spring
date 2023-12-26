package org.sunhb.beans.factory.config;

import org.sunhb.beans.PropertyValues;

/**
 * @author: SunHB
 * @createTime: 2023/12/23 下午9:58
 * @description:
 */
public class BeanDefinition {
    private Class beanClass;
    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues!= null ? propertyValues: new PropertyValues();
    }

    public BeanDefinition(Class beanClass) {
        this(beanClass,null);
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }
}
