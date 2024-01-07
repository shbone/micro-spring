package org.sunhb.beans.context;

import org.sunhb.beans.BeanException;

/**
 * @author: SunHB
 * @createTime: 2024/01/06 下午6:00
 * @description:
 */
public interface ConfigurableApplicationContext extends ApplicationContext{
    //刷新容器
    void refresh() throws BeanException;
}
