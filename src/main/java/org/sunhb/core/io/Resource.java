package org.sunhb.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: SunHB
 * @createTime: 2023/12/31 下午4:42
 * @description: 资源的抽象和访问接口
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
