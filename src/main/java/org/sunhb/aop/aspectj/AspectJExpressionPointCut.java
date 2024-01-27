package org.sunhb.aop.aspectj;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.sunhb.aop.ClassFilter;
import org.sunhb.aop.MethodMatcher;
import org.sunhb.aop.PointCut;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: SunHB
 * @createTime: 2024/01/16 下午7:59
 * @description:
 */
public class AspectJExpressionPointCut implements ClassFilter, MethodMatcher, PointCut {
    // 原始切面的功能函数？
    private static final Set<PointcutPrimitive> SUPPORT_PRIMITIVES = new HashSet<>();
    static {
        SUPPORT_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    }

    private final PointcutExpression pointcutExpression;

    public AspectJExpressionPointCut(String expression) {
        //TODO: 理解pointCut 的注入
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORT_PRIMITIVES, this.getClass().getClassLoader());
        pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }

    @Override
    public boolean matches(Class<?> clazz) {
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }

    @Override
    public boolean match(Method method, Class<?> clazz) {
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }
}
