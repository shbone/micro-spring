package org.sunhb.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: SunHB
 * @createTime: 2023/12/26 下午8:28
 * @description:
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue){
        propertyValueList.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }


}
