package org.sunhb.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import org.sunhb.beans.BeanException;
import org.sunhb.beans.PropertyValue;
import org.sunhb.beans.factory.DisposableBean;
import org.sunhb.beans.factory.InitializingBean;
import org.sunhb.beans.factory.config.BeanDefinition;
import org.sunhb.beans.factory.config.BeanPostProcessor;
import org.sunhb.beans.factory.config.BeanReference;

import java.lang.reflect.Method;

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
            //实例化bean
            bean = createBeanInstance(beanDefinition);
            //bean添加属性
            applyPropertyValues(beanName,bean,beanDefinition);
            //bean = beanClass.newInstance();
            //初始化bean
            InitializeBean(beanName,bean,beanDefinition);
        } catch (Exception e){
            throw new BeanException("Instantiation of bean failed",e);
        }
        registerDisposableBeanIfNecessary(beanName,bean,beanDefinition);

        addSingletonBeanMap(beanName,bean);
        return bean;
    }

    protected Object InitializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 执行bean BeanPostProcessor前处理
        Object wrappedBean =applyBeanPostProcessorsBeforeInitialization(bean,beanName);
         //TODO:bean初始化方法
        try{
            invokeInitMethods(beanName,wrappedBean,beanDefinition);
        }catch (Throwable ex){
            throw new BeanException("Invocation of init method of bean ["+beanName+"] failed",ex);
        }
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

    protected void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Throwable {

        if(bean instanceof InitializingBean){
            ((InitializingBean)bean).afterPropertiesSet();
        }
        String initMethodName = beanDefinition.getInitMethodName();
        //bean自定义插入property
        if(StrUtil.isNotEmpty(initMethodName) && !(bean instanceof InitializingBean && "afterPropertiesSet".equals(initMethodName))){
            Method initMethod = ClassUtil.getPublicMethod(beanDefinition.getBeanClass(), initMethodName);
            if(initMethod == null){
                throw new BeanException("Can not find init-method '"+initMethodName +"' on bean with name '"+beanName+"='");
            }
            //TODO: initMethod invoke
            initMethod.invoke(bean);
        }
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
    /**
     * @Author sunhb
     * @Description 注册有销毁方法的bean,即bean继承自DisposableBean的或者自有的销毁方法
     * @Date 2024/1/13 下午4:41
     * @Param
     * @param beanName
     * @param bean
     * @param beanDefinition
     * @return void
     **/
    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }

}
