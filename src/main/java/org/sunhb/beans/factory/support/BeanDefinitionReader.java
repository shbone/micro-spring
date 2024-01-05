package org.sunhb.beans.factory.support;

import javafx.beans.property.StringProperty;
import org.sunhb.beans.BeanException;
import org.sunhb.core.io.Resource;
import org.sunhb.core.io.ResourceLoader;

/**
 * @author: SunHB
 * @createTime: 2024/01/01 下午10:04
 * @description: 定义BeanDefiniton的接口
 */
public interface BeanDefinitionReader {
    ResourceLoader getResourceLoader();

    BeanDefinitionRegister  getRegistry();

    void loadBeanDefinitions(Resource resource) throws BeanException;

    void loadBeanDefinitions(String filePath) throws BeanException;

    void loadBeanDefintions(String[] filePaths) throws BeanException;
}
