package org.sunhb.beans.context.support;

import org.sunhb.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author: SunHB
 * @createTime: 2024/01/07 下午4:09
 * @description:
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{
    //TODO: 为什么和AbstractApplicationContext 分层实现 ？？
    private DefaultListableBeanFactory beanFactory;
    // 实现了BeanFactory的构建
    public void refreshBeanFactory(){
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    protected DefaultListableBeanFactory createBeanFactory(){
        return new DefaultListableBeanFactory();
    }
    /**
     * @Author sunhb
     * @Description 加载BeanDefinition
     * @Date 2024/1/7 下午4:19
     * @Param
     * @param beanFactory
     * @return void
     **/
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    public DefaultListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
