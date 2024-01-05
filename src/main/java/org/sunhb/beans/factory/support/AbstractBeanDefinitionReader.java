package org.sunhb.beans.factory.support;

import org.sunhb.beans.BeanException;
import org.sunhb.beans.factory.config.BeanDefinition;
import org.sunhb.core.io.DefaultResourceLoader;
import org.sunhb.core.io.ResourceLoader;

/**
 * @author: SunHB
 * @createTime: 2024/01/01 下午10:03
 * @description:
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final  BeanDefinitionRegister register;

    private ResourceLoader resourceLoader;

    //TODO:??? protected ？？？
    protected AbstractBeanDefinitionReader(BeanDefinitionRegister register) {
        this(register,new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegister register, ResourceLoader resourceLoader) {
        this.register = register;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegister getRegistry() {
        return register;
    }

    @Override
    public void loadBeanDefintions(String[] filePaths) throws BeanException {
        for(String filePath:filePaths){
            loadBeanDefinitions(filePath);
        }
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }
}
