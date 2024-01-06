package org.sunhb.bean;

/**
 * @author: SunHB
 * @createTime: 2023/12/28 下午8:18
 * @description:
 */
public class Plane {
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
}
