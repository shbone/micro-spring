package org.sunhb.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author: SunHB
 * @createTime: 2024/01/21 下午9:40
 * @description: TODO:AdvisedSupport 的功能
 */
public class AdvisedSupport {
    private TargetSource targetSource;

    private MethodMatcher methodMatcher;

    private MethodInterceptor methodInterceptor;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }
}
