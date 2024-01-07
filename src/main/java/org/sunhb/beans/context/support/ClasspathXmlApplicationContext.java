package org.sunhb.beans.context.support;

import org.sunhb.beans.BeanException;

/**
 * @author: SunHB
 * @createTime: 2024/01/07 下午4:26
 * @description:
 */
public class ClasspathXmlApplicationContext extends AbstractXmlApplicationContext{
    private String[] configLocations;
    @Override
    protected String[] getConfigLocations() throws BeanException {
        return configLocations;
    }

    public ClasspathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    public ClasspathXmlApplicationContext(String configrationLocation){
        this(new String[]{configrationLocation});
    }


}
