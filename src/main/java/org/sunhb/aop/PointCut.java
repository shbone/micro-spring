package org.sunhb.aop;

/**
 * @author: SunHB
 * @createTime: 2024/01/16 下午7:57
 * @description:
 */
public interface PointCut {
    ClassFilter getClassFilter();
    MethodMatcher getMethodMatcher();
}
