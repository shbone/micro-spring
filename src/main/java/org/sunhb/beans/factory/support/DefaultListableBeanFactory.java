package org.sunhb.beans.factory.support;

import org.sunhb.beans.BeanException;
import org.sunhb.beans.factory.ConfigurableListableBeanFactory;
import org.sunhb.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: SunHB
 * @createTime: 2023/12/24 上午11:35
 * @description:
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory
        implements ConfigurableListableBeanFactory,BeanDefinitionRegister{
    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<String,BeanDefinition>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefiniton) {
        beanDefinitionMap.put(beanName,beanDefiniton);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeanException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition ==null){
            throw new BeanException(beanName+" is not defined!");
        }
        return beanDefinition;
    }

    @Override
    public void preInstantiateSingletons() throws BeanException {
        beanDefinitionMap.keySet().forEach(this::getBean);
    }

    @Override
    public boolean containsBeanDefiniton(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public String[] getBeanDefinitonNames() {
        Set<String> beanNames = beanDefinitionMap.keySet();
        return beanNames.toArray(new String[beanNames.size()]);
    }

    @Override
    public <T> Map<String, T> getBeansofType(Class<T> type)  {
        Map<String,T> map = new HashMap<>();
        beanDefinitionMap.forEach((beanName,beanDefinition)->{
            Class beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)){

                T bean = (T) getBean(beanName);
                map.put(beanName,bean);
            }
        });
        return map;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }
}
