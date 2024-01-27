package org.sunhb.aop;

/**
 * @author: SunHB
 * @createTime: 2024/01/21 下午9:41
 * @description: 被代理的对象
 */
public class TargetSource {
    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }

    public Class<?>[] getTargetClass(){
        return this.target.getClass().getInterfaces();
    }
}
