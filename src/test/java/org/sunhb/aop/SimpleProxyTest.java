package org.sunhb.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.jupiter.api.Test;
import org.sunhb.aop.aspectj.AspectJExpressionPointCut;
import org.sunhb.aop.framework.ReflectiveMethodInvocation;
import org.sunhb.service.WorldService;
import org.sunhb.service.WorldServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: SunHB
 * @createTime: 2024/01/27 下午3:17
 * @description: reference https://developer.aliyun.com/article/855292
 */
public class SimpleProxyTest {
    @Test
    public void testSimpleProxy(){
        WorldServiceImpl worldService = new WorldServiceImpl();
        //方法匹配器
        MethodMatcher methodMatcher = new AspectJExpressionPointCut("execution(* org.sunhb.service.WorldService.*(..))").getMethodMatcher();
        WorldService proxyInstance = (WorldService)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), worldService.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (methodMatcher.match(method,worldService.getClass())) {
                    long start = System.currentTimeMillis();
                    //方法拦截器
                    MethodInterceptor interceptor = innovation -> {
                        try {
                            return innovation.proceed();
                        } finally {
                            System.out.println("cost time:" + (System.currentTimeMillis() - start) + " ms");
                            System.out.println("method name:" + innovation.getMethod().getName());
                            System.out.println("End\n");
                        }
                    };
                    return interceptor.invoke(new ReflectiveMethodInvocation(worldService, method, args));
                }
                return method.invoke(worldService, args);
            }
        });
        proxyInstance.flush();
        proxyInstance.disappear();
    }
}
