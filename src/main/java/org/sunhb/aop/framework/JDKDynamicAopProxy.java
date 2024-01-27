package org.sunhb.aop.framework;

import org.aopalliance.intercept.MethodInterceptor;
import org.sunhb.aop.AdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.ReflectPermission;

/**
 * @author: SunHB
 * @createTime: 2024/01/21 下午9:37
 * @description: InvocationHandler 处理代理对象接口
 */
public class JDKDynamicAopProxy implements AopProxy, InvocationHandler {


    private AdvisedSupport advisedSupport;

    public JDKDynamicAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(advisedSupport.getMethodMatcher().match(method,advisedSupport.getTargetSource().getTarget().getClass())){
            MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(),method,args));
        }
        return method.invoke(advisedSupport.getTargetSource().getTarget(),args);
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(getClass().getClassLoader(),this.advisedSupport.getTargetSource().getTargetClass(),this);
    }

}
