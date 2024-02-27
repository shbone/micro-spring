## bean 定义和注册
> 分支名称：bean-definition-and-bean-definition-register

主要增加如下类：

- BeanDefinition，顾名思义，用于定义bean信息的类，包含bean的class类型、构造参数、属性值等信息，每个bean对应一个BeanDefinition的实例。简化BeanDefinition仅包含bean的class类型。
- BeanDefinitionRegistry，BeanDefinition注册表接口，定义注册BeanDefinition的方法。
- SingletonBeanRegistry及其实现类DefaultSingletonBeanRegistry，定义添加和获取单例bean的方法。
- BeanFactory, bean工厂接口，包含获取bean方法

bean容器(DefaultListableBeanFactory)作为BeanDefinitionRegistry和SingletonBeanRegistry的实现类，具备两者的能力。向bean容器中注册BeanDefinition后，使用bean时才会实例化。

![bean definition 类关系图](./asset/pics/micro-s1.png)


```java
public class TestBeanFactory {

    @Test
    public void beanDefinitionAndBeanRegisterTest() throws BeanException {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        BeanDefinition helloWorldBeanDefinition = new BeanDefinition(HelloWorldBean.class);
        defaultListableBeanFactory.registerBeanDefinition("helloWorldBean",helloWorldBeanDefinition);
        HelloWorldBean helloWorldBean = (HelloWorldBean)defaultListableBeanFactory.getBean("helloWorldBean");
        Assert.assertEquals(helloWorldBean.hello(),"HelloWorldBean.hello");
    }

}
```

## bean实例化策略

> 分支名称：instantiation-strategy

bean = beanClass.newInstance(); 仅适用无参构造bean的情况，需要完善实例化策略

![instantiation实例化接口实现](./asset/pics/instantiation-strategy_1.png)

主要增加如下类：
抽象出一个bean实例化策略的接口InstantiationStrategy，有两个实现类
- SimpleInstantiationStrategy，通过构造函数的方式进行实例化
- CglibSubClassingInstatiationStrategy，通过Cglib动态代理实现子类

## bean属性

> 分支名称：decorate-bean-with-property-values 

BeanDefinition 中添加对应bean属性的PropertyValues 成员变量
PropertyValue 包括 name和value 两个属性

测试示例
```java
public class BeanWithPropertyValuesTest {

    @Test
    public void testBeanWithPropertyValues() throws BeanException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name","helloWorld"));
        propertyValues.addPropertyValue(new PropertyValue("age",18));
        BeanDefinition beanDefinition = new BeanDefinition(Person.class,propertyValues);
        beanFactory.registerBeanDefinition("person",beanDefinition);
        Person person = (Person)beanFactory.getBean("person");
        Assertions.assertNotNull(person);
        Assertions.assertEquals(person.getAge(),18);
        Assertions.assertEquals(person.getName(),"helloWorld");
    }
}

```

## bean中注入bean
> 分支: bean-with-bean

- 增加BeanReference类，表示一个bean对另一个bean的引用。如果beanA 的属性 PropertyValue#value为beanDefinition类型，依赖bean B，则先实例化
bean B,再作为PropertyValue属性注入。暂时不支持循环依赖.....

``` text

protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) throws BeanException {
    try {
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValueList()) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            //bean A 依赖bean B , 实例化bean B后再实例化bean A
            if(value instanceof BeanReference){
                BeanReference beanReference =(BeanReference)value;
                value = getBean(beanReference.getBeanName());
            }
            //通过反射设置属性
            BeanUtil.setFieldValue(bean, name, value);
        }
    } catch (Exception ex) {
        throw new BeanException("Error setting property values for bean: " + beanName, ex);
    }
}
```
测试示例
```java
public class BeanWithPropertyValuesTest {
    @Test
    public void testPopulateBeanWithBean() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //注册Plane实例
        PropertyValues propertyValuesForPlane = new PropertyValues();
        propertyValuesForPlane.addPropertyValue(new PropertyValue("nation", "CN"));
        BeanDefinition planeBeanDefinition = new BeanDefinition(Plane.class, propertyValuesForPlane);
        beanFactory.registerBeanDefinition("plane", planeBeanDefinition);

        //注册Person实例
        PropertyValues propertyValuesForPerson = new PropertyValues();
        propertyValuesForPerson.addPropertyValue(new PropertyValue("name", "derek"));
        propertyValuesForPerson.addPropertyValue(new PropertyValue("age", 18));
        //Person实例依赖Plane实例
        propertyValuesForPerson.addPropertyValue(new PropertyValue("plane", new BeanReference("plane")));
        BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValuesForPerson);
        beanFactory.registerBeanDefinition("person", beanDefinition);

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
        Assertions.assertEquals(person.getName(),"derek");
        Assertions.assertEquals(person.getAge(),18);
        Plane plane = person.getPlane();
        Assertions.assertNotNull(plane);
        //assertThat(car).isNotNull();
        Assertions.assertEquals(plane.getNation(),"CN");
        //assertThat(car.getBrand()).isEqualTo("porsche");
    }
}
```

## 资源和资源加载器
> 分支名称：