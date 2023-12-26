package org.sunhb.beans;

/**
 * @author: SunHB
 * @createTime: 2023/12/26 下午8:27
 * @description:
 */
public class PropertyValue {
    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
