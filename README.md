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