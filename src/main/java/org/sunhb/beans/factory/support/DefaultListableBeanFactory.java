package org.sunhb.beans.factory.support;

import org.sunhb.beans.BeanException;
import org.sunhb.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: SunHB
 * @createTime: 2023/12/24 上午11:35
 * @description:
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegister{
    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<String,BeanDefinition>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefiniton) {
        beanDefinitionMap.put(beanName,beanDefiniton);
    }

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeanException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition ==null){
            throw new BeanException(beanName+"is not defined!");
        }
        return beanDefinition;
    }
}
