package org.sunhb.aop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sunhb.aop.aspectj.AspectJExpressionPointCut;
import org.sunhb.service.HelloWorldService;

import java.lang.reflect.Method;

/**
 * @author: SunHB
 * @createTime: 2024/01/16 下午8:28
 * @description:
 */
public class PoinCutExpressionTest {
    @Test
    public void testPointCutExpression() throws NoSuchMethodException {
        AspectJExpressionPointCut pointCut = new AspectJExpressionPointCut("execution(* org.sunhb.service.HelloWorldService.*(..))");
        Class<HelloWorldService> serviceClass = HelloWorldService.class;

        Method hello = serviceClass.getDeclaredMethod("hello");

        Assertions.assertTrue(pointCut.matches(serviceClass));
        Assertions.assertTrue(pointCut.match(hello,serviceClass));
    }
}
