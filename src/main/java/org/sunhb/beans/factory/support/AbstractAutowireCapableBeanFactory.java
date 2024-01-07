package org.sunhb.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import org.sunhb.beans.BeanException;
import org.sunhb.beans.PropertyValue;
import org.sunhb.beans.factory.config.BeanDefinition;
import org.sunhb.beans.factory.config.BeanPostProcessor;
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
            //初始化bean
            InitializeBean(beanName,bean,beanDefinition);
        } catch (Exception e){
            throw new BeanException("Instantiation of bean failed",e);
        }
        addSingletonBeanMap(beanName,bean);
        return bean;
    }

    protected Object InitializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 执行bean BeanPostProcessor前处理
        Object wrappedBean =applyBeanPostProcessorsBeforeInitialization(bean,beanName);
         //TODO:bean初始化方法
         invokeInitMethods(beanName,wrappedBean,beanDefinition);
         //执行bean BeanPostProcessor后处理
         wrappedBean = applyBeanPostProcessorsAfterInitialization(bean,beanName);

         return wrappedBean;
    }

    private Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) {
        Object current = existingBean;
        for(BeanPostProcessor beanPostProcessor:getBeanPostProcessorList() ){
            current = beanPostProcessor.postProcessBeanAfterInitilization(existingBean,beanName);
        }
        return current;
    }

    private Object applyBeanPostProcessorsBeforeInitialization(Object bean, String beanName) {
        Object current = bean;
        for(BeanPostProcessor beanPostProcessor:getBeanPostProcessorList()){
             current =beanPostProcessor.postProcessBeanBeforeInitilization(bean,beanName);
        }
        return current;
    }

    protected void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) {
        //TODO 后面会实现
        System.out.println("执行bean[" + beanName + "]的初始化方法");
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
