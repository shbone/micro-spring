package org.sunhb.core.io;

import java.awt.image.RasterOp;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.NoSuchFileException;

/**
 * @author: SunHB
 * @createTime: 2024/01/01 上午11:59
 * @description:
 */
public class ClassPathResource implements Resource {
    private String path;

    public ClassPathResource(String path) {
        this.path = path;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(this.path);
        if(resourceAsStream == null){
            throw new FileNotFoundException(this.path+" cannot be opened because it does not exist");
        }
        return resourceAsStream;
    }
}
