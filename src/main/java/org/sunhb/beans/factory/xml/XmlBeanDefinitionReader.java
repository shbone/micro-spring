package org.sunhb.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.sun.deploy.util.StringUtils;
import org.sunhb.beans.BeanException;
import org.sunhb.beans.PropertyValue;
import org.sunhb.beans.PropertyValues;
import org.sunhb.beans.factory.config.BeanDefinition;
import org.sunhb.beans.factory.config.BeanReference;
import org.sunhb.beans.factory.support.AbstractBeanDefinitionReader;
import org.sunhb.beans.factory.support.BeanDefinitionRegister;
import org.sunhb.core.io.Resource;
import org.sunhb.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: SunHB
 * @createTime: 2024/01/01 下午10:02
 * @description:
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public static final String BEAN_ELEMENT = "bean";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String ID_ATTRIBUTE = "id";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String REF_ATTRIBUTE = "ref";


    public static final String INIT_METHOD_ATTRIBUTE = "init-method";
    public static final String DESTROY_METHOD_ATTRIBUTE = "destroy-method";
    public XmlBeanDefinitionReader(BeanDefinitionRegister register) {
        super(register);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegister register, ResourceLoader resourceLoader) {
        super(register, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeanException {
        try {
            InputStream inputStream = resource.getInputStream();
            doLoadBeanDefinitions(inputStream);
            inputStream.close();
        } catch (IOException e) {
            throw new BeanException("IOException parsing  XML document from " + resource, e);
        }

    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws BeanException {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            //TOOD:为什么判断为 XML 元素？？
            if (childNodes.item(i) instanceof Element) {
                if (BEAN_ELEMENT.equals((childNodes.item(i)).getNodeName())) {
                    Element bean = (Element) childNodes.item(i);
                    String id = bean.getAttribute(ID_ATTRIBUTE);
                    String name = bean.getAttribute(NAME_ATTRIBUTE);
                    String className = bean.getAttribute(CLASS_ATTRIBUTE);
                    String destroyMethod = bean.getAttribute(DESTROY_METHOD_ATTRIBUTE);
                    String initMethod = bean.getAttribute(INIT_METHOD_ATTRIBUTE);
                    Class<?> clazz = null;
                    try {
                        clazz = Class.forName(className);
                    } catch (ClassNotFoundException e) {
                        throw new BeanException("Can not find class [" + className + "]");
                    }
                    //优先以id 作为beanName,若id为空，以name作为beanName
                    String beanName = StrUtil.isNotEmpty(id) ? id : name;
                    if(StrUtil.isEmpty(beanName)){
                        //如果id和name都为空，将类名的第一个字母转为小写后作为bean的名称
                        beanName = StrUtil.lowerFirst(clazz.getSimpleName());
                    }
                    BeanDefinition beanDefinition = new BeanDefinition(clazz);
                    beanDefinition.setDestroyMethodName(destroyMethod);
                    beanDefinition.setInitMethodName(initMethod);
                    //解析bean下的属性
                    for(int j =0 ;j< bean.getChildNodes().getLength();j++){
                        if(bean.getChildNodes().item(j) instanceof Element){
                            if(PROPERTY_ELEMENT.equals(((Element) bean.getChildNodes().item(j)).getNodeName())){
                                Element property = (Element)bean.getChildNodes().item(j);
                                String nameAttribute = property.getAttribute(NAME_ATTRIBUTE);
                                String valueAttribute = property.getAttribute(VALUE_ATTRIBUTE);
                                String refAttribute = property.getAttribute(REF_ATTRIBUTE);

                                if(StrUtil.isEmpty(nameAttribute)){
                                    throw new BeanException("The name attribute cannot be null or empty");
                                }

                                Object value = valueAttribute;
                                //替换value 为相应的ref bean
                                if (StrUtil.isNotEmpty(refAttribute)) {
                                    value = new BeanReference(refAttribute);
                                }
                                PropertyValue propertyValue = new PropertyValue(nameAttribute, value);
                                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                            }
                        }
                    }
                    if(getRegistry().containsBeanDefiniton(beanName)){
                        throw new BeanException("Duplicate ["+beanName+"] is not allowed!");
                    }
                    getRegistry().registerBeanDefinition(beanName,beanDefinition);
                }
            }
        }
    }

    @Override
    public void loadBeanDefinitions(String filePath) throws BeanException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(filePath);
        loadBeanDefinitions(resource);
    }
}
