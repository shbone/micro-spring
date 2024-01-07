package org.sunhb.beans.context.support;

import org.sunhb.beans.BeanException;
import org.sunhb.beans.factory.support.DefaultListableBeanFactory;
import org.sunhb.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author: SunHB
 * @createTime: 2024/01/07 下午4:18
 * @description:
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory,this);
        String[] configLocations = getConfigLocations();
        if(configLocations != null){
            xmlBeanDefinitionReader.loadBeanDefintions(configLocations);
        }else {
            throw new BeanException(configLocations +"is a invalid configuration file path!");
        }


    }

    protected abstract String[] getConfigLocations()throws BeanException;
}
