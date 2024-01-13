package org.sunhb.bean;

import org.sunhb.beans.BeanException;
import org.sunhb.beans.factory.DisposableBean;
import org.sunhb.beans.factory.InitializingBean;

/**
 * @author derekyi
 * @date 2020/11/24
 */
public class Person implements InitializingBean, DisposableBean {
	private Plane plane;
	private String name;

	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("I was born in the method named Person.afterPropertiesSet");
	}
	public void customDestroyMethod(){
		System.out.println("I destroyed in the method named Person.customDestroyMethod");
	}

	public void customInitMethod(){
		System.out.println("I inited in the method named Person.customInitMethod");
	}
	@Override
	public void destroy() throws BeanException {
		System.out.println("I destroyed in the method named destroy");
	}
}
