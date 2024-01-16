package org.sunhb.ioc;

import org.junit.jupiter.api.Test;
import org.sunhb.beans.context.support.ClasspathXmlApplicationContext;

/**
 * @author: SunHB
 * @createTime: 2024/01/07 下午9:41
 * @description:
 */
public class InitAndDestroyMethodTest {
    @Test
    public void testInitAndDestroyMethod(){
        ClasspathXmlApplicationContext applicationContext = new ClasspathXmlApplicationContext("classpath:init-and-destroy-method.xml");
        applicationContext.registerShutdownHook();
    }
}
