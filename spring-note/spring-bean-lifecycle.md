`Bean`的完整生命周期经历了各种方法调用，这些方法可以划分为以下几类：

1. **Bean自身**的方法：包括了Bean本身调用的方法和通过配置文件中<bean>的`init-method`和`destroy-method`指定的方法，使用`@PostConstruct`和`@PreDestroy`注解方法
2. **Bean级**生命周期接口方法：包括了`BeanNameAware`、`BeanFactoryAware`、`InitializingBean`和`DiposableBean`这些接口的方法
3. **容器级**生命周期接口方法：包括了`InstantiationAwareBeanPostProcessor` 和 `BeanPostProcessor` 这两个接口实现，一般称它们的实现类为“后处理器”。
4. **工厂后处理器**接口方法：包括了`AspectJWeavingEnabler`, `ConfigurationClassPostProcessor`, `CustomAutowireConfigurer`等等非常有用的工厂后处理器接口的方法。工厂后处理器也是容器级的。在应用上下文装配配置文件之后立即调用



![](pic/spring-bean-lifecycle1.png)

![](pic/spring-bean-lifecycle2.png)