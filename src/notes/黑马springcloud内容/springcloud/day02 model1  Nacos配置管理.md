# day02 model01  Nacos配置管理

##### 在Nacos中添加配置信息

在配置管理--》配置列表中 点击添加按钮

在弹出表单中填写配置信息 填写配置问价的id   ：【服务名称】-【profile】.【后缀名】 

​		eg: userservice-local.yaml





分组，默认即可

配置格式，选为yaml.  配置内容放上需要热更新的内容。



在读取时候要优先于application.yaml    即使用bootstrap.yaml的读取优先级是优于application.yaml的





##### 引入nacos中的配置依赖管理 【userservice 的】

1. 引入Nacos的配置管理客户端依赖： 

   ```xml
   <!--        nacos的配置管理依赖-->
           <dependency>
               <groupId>com.alibaba.cloud</groupId>
               <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
           </dependency>
   ```

   

2. 在resources下创建bootstrap.yaml文件

```yaml
spring:
  application:
    name: userservice
  profiles:
    active: dev   #环境
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848        #nacos地址
      config:
        file-extension: yaml        #文件后缀名
```



### 将配置交给Nacos管理的步骤

1. 在Nacos中添加配置文件
2. 在微服务中引入nacos的config依赖
3. 在微服务中添加bootstrap.yaml,配置nacos地址、当前环境、服务名称、文件后缀名。这些决定了程序启动时去nacos读取哪个文件。

#### 配置自动刷新

nacos中的配置文件变更后，微服务无需重启就可以感知。不过需要通过下面两种配置实现：

* 方式一：在@value注入的变量所在的类上添加注解@RefreshScope

  ```java
  @Slf4j
  @RestController
  @RefreshScope
  @RequestMapping("/user")
  public class UserController {
  
      @Autowired
      private UserService userService;
  
      @Value("${pattern.dateformat}")
      private String dateformat;
  
  
      @GetMapping("now")
      public String now(){
          return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformat));
              }
              }
  ```

  

* 方式二：使用@ConfigurationProperties注解

```java
@Data
@Component      //注册成组件，让其他的类可以调用
@ConfigurationProperties(prefix = "pattern")
public class DateFormatProperties {
    private String dateformat;
}
```



## 多环境配置共享

不管怎么读取，userservice.yaml文件是需要读取的    在nacos中新建一个userservice.yaml文件，写配置即可



## Nacos集群搭建