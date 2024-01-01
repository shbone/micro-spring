package org.sunhb;

import cn.hutool.core.io.IoUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sunhb.core.io.DefaultResourceLoader;
import org.sunhb.core.io.FileSystemResource;
import org.sunhb.core.io.Resource;
import org.sunhb.core.io.URLResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: SunHB
 * @createTime: 2024/01/01 下午12:03
 * @description:
 */
public class ResourceAndResourceLoaderTest {
    @Test
    public void testResourceLoader() throws IOException {
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        // 加载classpath下的资源，classpath读取 resources文件夹下的文件
        Resource resource = defaultResourceLoader.getResource("classpath:testMain.txt");
        InputStream inputStream = resource.getInputStream();
        String readUtf8 = IoUtil.readUtf8(inputStream);
        System.out.println(readUtf8);

        //加载网络URL资源
        Resource urlResource = defaultResourceLoader.getResource("https://www.baidu.com");
        Assertions.assertInstanceOf(URLResource.class,urlResource,"加载结果类型错误...");
        InputStream urlResourceInputStream = urlResource.getInputStream();
        String urlStream = IoUtil.readUtf8(urlResourceInputStream);
        System.out.println(urlStream);

        //加载系统文件
        Resource fsResource = defaultResourceLoader.getResource("src/test/java/org/sunhb/testPath.java");
        Assertions.assertInstanceOf(FileSystemResource.class,fsResource,"加载结果类型错误...");
        InputStream fsResourceInputStream = fsResource.getInputStream();
        String readUtf81 = IoUtil.readUtf8(fsResourceInputStream);
        System.out.println(readUtf81);

    }
}
