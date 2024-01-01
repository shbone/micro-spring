package org.sunhb.core.io;

/**
 * @author: SunHB
 * @createTime: 2024/01/01 上午11:49
 * @description: 资源加载器
 */
public interface ResourceLoader {
    Resource getResource(String location);
}
