package org.sunhb.core.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: SunHB
 * @createTime: 2024/01/01 上午11:50
 * @description:
 */
public class DefaultResourceLoader implements ResourceLoader{
    private static final String CLASSPATH_URL_PREFIX = "classpath:";

    @Override
    public Resource getResource(String location) {
        if(location.startsWith(CLASSPATH_URL_PREFIX)){
            //classpath下的资源
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try{
                //URL资源进行处理
                URL url = new URL(location);
                return new URLResource(url);
            }catch (MalformedURLException exception){
                //系统文件进行处理
                return new FileSystemResource(location);
            }
        }
    }
}
