package org.sunhb.aop;

import org.junit.jupiter.api.Test;
import org.sunhb.aop.aspectj.AspectJExpressionPointCut;
import org.sunhb.aop.framework.JDKDynamicAopProxy;
import org.sunhb.common.WorldServiceInterceptor;
import org.sunhb.service.WorldService;
import org.sunhb.service.WorldServiceImpl;

import java.lang.annotation.Target;

/**
 * @author: SunHB
 * @createTime: 2024/01/21 下午9:34
 * @description:
 */
public class JDKDynamicProxyTest {

    @Test
    public void testJDKDynamicProxy(){
        WorldService worldService = new WorldServiceImpl();
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(worldService);
        WorldServiceInterceptor worldServiceInterceptor = new WorldServiceInterceptor();
        MethodMatcher methodMatcher = new AspectJExpressionPointCut("execution(* org.sunhb.service.WorldService.flush(..))").getMethodMatcher();
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodMatcher(methodMatcher);
        advisedSupport.setMethodInterceptor(worldServiceInterceptor);

        WorldService proxy = (WorldService)new JDKDynamicAopProxy(advisedSupport).getProxy();
        proxy.flush();
    }
}
