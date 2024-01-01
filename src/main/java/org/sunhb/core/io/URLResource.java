package org.sunhb.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author: SunHB
 * @createTime: 2024/01/01 下午8:55
 * @description:
 */
public class URLResource implements Resource{
    private final URL url;

    public URLResource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection urlConnection = this.url.openConnection();
        try{
            InputStream inputStream = urlConnection.getInputStream();
            return inputStream;
        }catch (IOException ex){
            throw ex;
        }
    }
}
