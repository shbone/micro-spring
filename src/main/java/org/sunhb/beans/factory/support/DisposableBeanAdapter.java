package org.sunhb.beans.factory.support;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import org.sunhb.beans.BeanException;
import org.sunhb.beans.factory.DisposableBean;
import org.sunhb.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @author: SunHB
 * @createTime: 2024/01/11 下午11:52
 * @description:
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String destroyMethodName;

    private final String beanName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception{
        if(bean instanceof DisposableBean){
            ((DisposableBean)bean).destroy();
        }
        //TODO: bean instanceof DisposableBean 是实现了什么？、
        //避免同时继承自DisposableBean，且自定义方法与DisposableBean方法同名，销毁方法执行两次的情况
        if(StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(destroyMethodName))){
            //检索自定义销毁方法
            Method destroyMethod = ClassUtil.getPublicMethod(bean.getClass(), destroyMethodName);
            if(destroyMethod == null){
                throw new BeanException("Can not find a method name '"+destroyMethodName+"' in bean '"+beanName+"'");
            }
            //TODO: destroyMethod  invoke 实现的逻辑
            destroyMethod.invoke(bean);
        }

    }
}
