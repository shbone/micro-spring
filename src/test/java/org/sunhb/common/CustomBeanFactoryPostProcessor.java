package org.sunhb.common;

import org.sunhb.beans.BeanException;
import org.sunhb.beans.PropertyValue;
import org.sunhb.beans.PropertyValues;
import org.sunhb.beans.factory.ConfigurableListableBeanFactory;
import org.sunhb.beans.factory.config.BeanDefinition;
import org.sunhb.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @author: SunHB
 * @createTime: 2024/01/06 下午3:57
 * @description:
 */
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException {
        BeanDefinition person = beanFactory.getBeanDefinition("person");
        PropertyValues propertyValues = person.getPropertyValues();
        //person 的属性进行更改
        propertyValues.addPropertyValue(new PropertyValue("name","DuDan"));
    }
}
