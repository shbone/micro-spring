package org.sunhb.beans.factory;

import org.sunhb.beans.BeanException;

/**
 * @author: SunHB
 * @createTime: 2024/01/11 下午8:03
 * @description:
 */
public interface DisposableBean {
    void destroy() throws Exception;
}
