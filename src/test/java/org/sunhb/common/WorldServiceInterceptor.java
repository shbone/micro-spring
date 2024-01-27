package org.sunhb.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author: SunHB
 * @createTime: 2024/01/26 下午1:55
 * @description:
 */
public class WorldServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("Do something before...");
        Object result = methodInvocation.proceed();
        System.out.println("Do something after...");
        return result;
    }
}
