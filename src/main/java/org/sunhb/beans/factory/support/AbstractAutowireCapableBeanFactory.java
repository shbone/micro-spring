package org.sunhb.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import org.sunhb.beans.BeanException;
import org.sunhb.beans.PropertyValue;
import org.sunhb.beans.factory.config.BeanDefinition;
import org.sunhb.beans.factory.config.BeanReference;

/**
 * @author: SunHB
 * @createTime: 2023/12/23 下午10:21
 * @description:
 */
abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefiniton) throws BeanException {
        return doCreateBean(beanName,beanDefiniton);
    }

    protected  Object doCreateBean(String beanName, BeanDefinition beanDefinition) throws BeanException {
        //Class beanClass = beanDefiniton.getBeanClass();
        Object bean = null;
        try{
            bean = createBeanInstance(beanDefinition);
            //bean添加属性
            applyPropertyValues(beanName,bean,beanDefinition);
            //bean = beanClass.newInstance();
        } catch (Exception e){
            throw new BeanException("Instantiation of bean failed",e);
        }
        addSingletonBeanMap(beanName,bean);
        return bean;
    }
    /**
     * @Author sunhb
     * @Description bean 填充属性
     * @Date 2023/12/27 上午12:43
     * @Param
     * @param beanName
     * @param bean
     * @param beanDefinition
     * @return void
     **/
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) throws BeanException {
        try {
            for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValueList()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if(value instanceof BeanReference){
                    BeanReference beanReference =(BeanReference)value;
                    value = getBean(beanReference.getBeanName());
                }
                //通过反射设置属性
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception ex) {
            throw new BeanException("Error setting property values for bean: " + beanName, ex);
        }
    }

    protected Object createBeanInstance(BeanDefinition beanDefiniton) throws BeanException {
        return getInstantiationStrategy().instantiate(beanDefiniton);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

}
