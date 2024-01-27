package org.sunhb.service;

/**
 * @author: SunHB
 * @createTime: 2024/01/25 下午9:01
 * @description:
 */
public class WorldServiceImpl implements WorldService{
    @Override
    public void flush() {
        System.out.println("WorldServiceImpl.flush");
    }

    public void disappear(){
        System.out.println("WorldServiceImpl.disappear");
    }
}
