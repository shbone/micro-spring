package org.sunhb.aop;

import java.lang.reflect.Method;

/**
 * @author: SunHB
 * @createTime: 2024/01/16 下午7:55
 * @description: 函数方法匹配
 */
public interface MethodMatcher {
    boolean match(Method method, Class<?> clazz);
}
