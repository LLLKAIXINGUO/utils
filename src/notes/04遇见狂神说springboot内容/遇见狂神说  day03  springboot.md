# 遇见狂神说 day03  springboot

* JDBC
* **Mybatis 重点**
* **Druid**
* **Shiro**
* **Spring Security :安全   重点**
* 异步任务  ： 邮件发送，定时任务
* Swagger
* Dubbo+Zookeeper

创建项目时，选择上  jdbc   mysql  web的驱动等

```yaml
spring:
  datasource:
    username: root
    password: 1234
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/day14?useUnicode=true&characterEncoding=utf8
```



## 整合Druid数据源

```xml
先导包
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.2.6</version>
</dependency>
```



```yaml
spring:
  datasource:
    username: root
    password: 1234
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/day14?useUnicode=true&characterEncoding=utf-8
    type: com.alibaba.druid.pool.DruidDataSource
```



```java
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }
    //后台监控
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean =
                new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
        //后台需要有人登录，账号密码配置
        HashMap<String, String> initParameters = new HashMap<>();
        //增加配置  登录的key 是固定的loginUsername  loginPassword
        initParameters.put("loginUsername","admin");
        initParameters.put("loginPassword","123456");
        //允许谁可以访问
        initParameters.put("allow","");
        bean.setInitParameters(initParameters); //设置初始化参数
        return bean;
    }
}
```

**日志监控   **





## 整合mybatis

**导入包**

```xml
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.2.0</version>
</dependency>
```

### mapper包的扫描 ，有两种 

1. 在mapper接口上写上@Mapper

2. 在启动类上加入  @MapperScan("mapper包的包名")

3. ```yaml
   #mybatis
   mybatis:
     type-aliases-package: com.lym.pojo
     mapper-locations: classpath:mybatis/mapper/*.xml 
   ```

   



### SpringSecurity(安全)

在web开发中，安全属于第一位！  过滤器，拦截器

安全不属于一个功能性需求

做网站，安全应该在最开始的时候考虑





## springboot集成swagger

1. 导入相关依赖  swagger2       swagger-ui
2. 配置swaggerConfig  写一个config

