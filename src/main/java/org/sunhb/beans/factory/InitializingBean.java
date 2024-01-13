package org.sunhb.beans.factory;

/**
 * @author: SunHB
 * @createTime: 2024/01/11 下午7:29
 * @description:
 */
public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
