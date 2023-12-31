package org.sunhb.beans.factory.support;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.sunhb.beans.BeanException;
import org.sunhb.beans.factory.config.BeanDefinition;

/**
 * @author: SunHB
 * @createTime: 2023/12/26 下午7:29
 * @description:
 */
public class CglibSubClassingInstatiationStrategy implements InstantiationStrategy{
    /**
     * @Author sunhb
     * @Description CGLIB动态生成Bean子类
     * @Date 2023/12/26 下午7:30
     * @Param
     * @param beanDefinition
     * @return java.lang.Object
     **/
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeanException {
        //TODO 感兴趣的小伙伴可以实现下
        throw new UnsupportedOperationException("CGLIB instantiation strategy is not supported");
        //Enhancer enhancer = new Enhancer();
        //enhancer.setSuperclass(beanDefinition.getBeanClass());
        ////修改函数的回调函数
        //enhancer.setCallback((MethodInterceptor)(object, method, argsTmp, proxy)-> proxy.invokeSuper(object,argsTmp));
        //return enhancer.create();
    }
}
