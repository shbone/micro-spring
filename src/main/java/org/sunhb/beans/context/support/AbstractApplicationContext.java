package org.sunhb.beans.context.support;

import org.sunhb.beans.BeanException;
import org.sunhb.beans.context.ConfigurableApplicationContext;
import org.sunhb.beans.factory.ConfigurableListableBeanFactory;
import org.sunhb.beans.factory.config.BeanFactoryPostProcessor;
import org.sunhb.beans.factory.config.BeanPostProcessor;
import org.sunhb.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @author: SunHB
 * @createTime: 2024/01/07 下午1:21
 * @description:
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    private String[] configurations;

    @Override
    public void refresh() throws BeanException {
        //创建BeanFactory
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        //bean实例化前执行BeanFactoryPostProcessor
        // TODO:  AbstractApplicationContext为什么会继承beanFactoryPostProcessor ???
        invokeBeanFactoryPostProcessors(beanFactory);

        //注册BeanPostProcessors，在bean实例化之前
        registerBeanPostProcessors(beanFactory);

        //提前实例化bean
        //TODO: 为什么提前实例化bean?
        beanFactory.preInstantiateSingletons();
    }

    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansofType(BeanPostProcessor.class);
        if(beanPostProcessorMap == null){
            return ;
        }
        for(BeanPostProcessor beanPostProcessor:beanPostProcessorMap.values()){
            // TODO:???
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansofType(BeanFactoryPostProcessor.class);
        if(beanFactoryPostProcessorMap == null){
            return ;
        }
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * @return void
     * @Author sunhb
     * @Description 创建BeanFactory, 加载 BeanDefinition
     * @Date 2024/1/7 下午1:24
     * @Param
     **/
    protected abstract void refreshBeanFactory() throws BeanException;

    public abstract ConfigurableListableBeanFactory getBeanFactory();

    @Override
    public Object getBean(String name) throws BeanException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public <T> T getBean(String name, Class<T> reuqiredType) throws BeanException {
        return getBeanFactory().getBean(name,reuqiredType);
    }

    @Override
    public <T> Map<String, T> getBeansofType(Class<T> type) throws BeanException {
        return getBeanFactory().getBeansofType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }
}
