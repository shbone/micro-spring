package org.sunhb.beans.context;

import org.sunhb.beans.factory.HierarchicalBeanFactory;
import org.sunhb.beans.factory.ListableBeanFactory;
import org.sunhb.core.io.ResourceLoader;

/**
 * @author: SunHB
 * @createTime: 2024/01/06 下午6:00
 * @description:
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader {
}
