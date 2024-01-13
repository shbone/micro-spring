package org.sunhb.beans.factory.support;

import org.sunhb.beans.BeanException;
import org.sunhb.beans.factory.DisposableBean;
import org.sunhb.beans.factory.config.SingletonBeanRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: SunHB
 * @createTime: 2023/12/23 下午10:02
 * @description: 处理SinigletonBean实现类
 */
public class DefaultSingletonBeanRegister implements SingletonBeanRegister {
    private Map<String,Object> singletonBeanMap = new HashMap<String, Object>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();
    public Object getSingletonBean(String beanName) {
        return singletonBeanMap.get(beanName);
    }

    public void addSingletonBeanMap(String beanName,Object SingletonBean) {
        singletonBeanMap.put(beanName,SingletonBean);
    }
    //destroySingletons 继承 同级接口 ConfigrableBeanFacory
    // 继承DefaultSingletonBeanRegister 的 DefaultListableBeanFactory 同样实现了 destroySingletons
    public void destroySingletons(){
        ArrayList<String> beanNames = new ArrayList<>(disposableBeans.keySet());
        for(String beanName:beanNames){
            DisposableBean removeBean = disposableBeans.remove(beanName);
            try {
                removeBean.destroy();
            }catch (Exception ex){
                throw new BeanException("Destroy method on bean with name '"+beanName+"' throw a Exception...");
            }

        }
    }
    //TODO:??? registerDisposableBean 怎么用上的？
    public void registerDisposableBean(String beanName, DisposableBeanAdapter bean) {
        disposableBeans.put(beanName, bean);
    }
}
