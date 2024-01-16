package org.sunhb.aop;

/**
 * @author: SunHB
 * @createTime: 2024/01/16 下午7:55
 * @description:
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
