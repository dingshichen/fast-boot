# SpringBoot configure 相关注解的Demo

**应用到的注解：**
@Configuration

**@Configuation 学习顺序：**  
DemoConfiguration  
DemoScanConfiguration  
DemoOutConfiguration

**@Configuation 总结：**

@Configuation等价于`<Beans></Beans>`

@Bean等价于`<Bean></Bean>`

@ComponentScan等价于`<context:component-scan base-package="com.dxz.demo"/>`

其他开关注解也可以配合@Configuration使用，包括 @EnableAsync, @EnableScheduling, @EnableTransactionManagement, @EnableAspectJAutoProxy, @EnableWebMvc。