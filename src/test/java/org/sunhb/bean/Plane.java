package org.sunhb.bean;

import org.sunhb.beans.factory.DisposableBean;
import org.sunhb.beans.factory.InitializingBean;

/**
 * @author: SunHB
 * @createTime: 2023/12/28 下午8:18
 * @description:
 */
public class Plane implements DisposableBean, InitializingBean {
    private String nation;


    //public Plane(String nation) {
    //    this.nation = nation;
    //}
    private String brand;
    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "nation='" + nation + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Plane.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Plane.afterPropertiesSet");
    }

    public void customInitMethod(){
        System.out.println("Plane.customInitMethod");
    }
}
