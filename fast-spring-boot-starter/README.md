#**fast-spring-boot-starter**  
这是一个spring boot starter的写法例子
  

Spring Boot由众多Starter组成，随着版本的推移Starter家族成员也与日俱增。在传统Maven项目中通常将一些层、组件拆分为模块来管理， 以便相互依赖复用，在Spring Boot项目中我们则可以创建自定义Spring Boot Starter来达成该目的。

可以认为starter是一种服务——使得使用某个功能的开发者不需要关注各种依赖库的处理，不需要具体的配置信息， 由Spring Boot自动通过classpath路径下的类发现需要的Bean，并织入相应的Bean。  

###**第一步：** 

配置maven引用：  

```            
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>${spring.boot.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>

        <!-- @ConfigurationProperties annotation processing (metadata for IDEs)
         生成spring-configuration-metadata.json类，需要引入此类-->
        <!-- 注意其中 spring-boot-configuration-processor 的作用是编译时生成spring-configuration-metadata.json， 此文件主要给IDE使用，用于提示使用。如在intellij idea中，当配置此jar相关配置属性在application.yml， 你可以用ctlr+鼠标左键，IDE会跳转到你配置此属性的类中。 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-autoconfigure</artifactId>
        </dependency>
```

###**第二步：**  

创建一个你的自动配置类

```java
@Configuration
@ConditionalOnClass(DefaultWordServiceImpl.class)
@EnableConfigurationProperties(WordProperties.class)
public class WordAutoConfiguration {

	private final WordProperties wordProperties;

	@Autowired
	public WordAutoConfiguration(WordProperties wordProperties) {
		this.wordProperties = wordProperties;
	}

	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = "fast.word", value = "enabled", havingValue = "true")
	public WordService wordService(){
		return new DefaultWordServiceImpl(this.wordProperties.getPrefix(), this.wordProperties.getSuffix());
	}
}
```

###**第三步：**  

在classpath:META-INF下创建spring.factories文件  

注册你的配置类  

```properties
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
cn.dsc.faststarter.config.WordAutoConfiguration
```

###**使用方式：**  

将此项目安装到maven仓库，其他项目引入maven依赖

```
        <dependency>
            <groupId>cn.dsc</groupId>
            <artifactId>fast-spring-boot-starter</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```

配置文件开启配置

```yaml
fast:
  word:
    enabled: true
    prefix: Hello
    suffix: Starter
```