## day02、SpringCloudEureka注册中心

Eureka分为【服务端】和【客户端】

Eureka的作用   

1. eureka-server 注册中心  【每一个服务都会被记录了进来】
   * 记录服务信息
   * 心跳监控
2. eureka-client eureka客户端
   * provider：服务提供者，例如案例中共的user-service
     * 注册自己的信息到EurekaServer
     * 每隔30秒向EurekaServer发送心跳
   * Consumer:服务消费者，例如案例中的order-service
     * 根据服务名称从EurekaServer拉取服务列表
     * 基于服务列表做负载均衡，选中一个微服务后发起远程调用

## Eureka注册中心的实现

1. 搭建注册中心【搭建eureka服务步骤如下】

   1. 创建maven项目，引入spring-cloud-starter-netflix-eureka-server的依赖

      ```xml
      <dependency>
          <groupId>org.springframework.cloud</groupId>
          <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
      </dependency>
      ```

   2. 编写启动类，并添加@EnableEurekaServer注解作为eureka的服务的启动项

      ```java
      package cn.itcast.eureka;
      
      import org.springframework.boot.SpringApplication;
      import org.springframework.boot.autoconfigure.SpringBootApplication;
      import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
      
      @EnableEurekaServer
      @SpringBootApplication
      public class EurekaApplication {
          public static void main(String[] args) {
              SpringApplication.run(EurekaApplication.class,args);
          }
      }
      ```

   3. 添加application.yaml文件，编写下面的配置

      ```yaml
      server:
        port: 10086  # 服务端口
      spring:
        application:
          name: eurekaserver              #eureka的服务名称
      eureka:
        client:
          service-url:                 #eureka的地址信息
            defaultZone: http://127.0.0.1:10086/eureka
      ```

   

   

   ![1639962336402](G:%5CTYPORA%E6%96%87%E6%A1%A3%E5%A1%AB%E5%85%85%5C%E9%BB%91%E9%A9%ACspringcloud%E5%86%85%E5%AE%B9%5Cspringcloud%5Cday01%20model2%20SpringCloudEureka%E6%B3%A8%E5%86%8C%E4%B8%AD%E5%BF%83.assets%5C1639962336402.png)

   ## 服务发现


##### 在order-service完成服务拉取

##### 服务拉取是基于服务名称获取服务列表，然后再对服务列表做负载均衡

1. 修改OrderService的代码，修改访问的url路径，用服务名代替ip、端口：

   ```
   String url ="http://localhost:8081/user/"+order.getUserId();
   ```


```java
    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //2.利用resttemplate发起http请求
        String url = "http://localhost:8081/user/"+order.getUserId();
        User user = restTemplate.getForObject(url, User.class);
        order.setUser(user);
//         4.返回
        return order;
    }
============================>>>>这样做不适用于多集群，对其进行修改加优化
```

### 注册user-service

##### 将user-service服务注册到EurekaServer步骤如下：

1. 服务注册【将user-service服务注册到EurekaServer步骤如下：】

   1. 在user-service项目中引入spring-cloud-starter-netflix-eureka-client的依赖

      ```xml
      <dependency>
          <groupId>org.springframework.cloud</groupId>
          <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
      </dependency>
      ```

   2. 在application.yaml文件，编写下面的配置

      ```yaml
      server:
        port: 8081
      spring:
        datasource:
          url: jdbc:mysql://localhost:3306/cloud_user?useSSL=false
          username: root
          password: 1234
          driver-class-name: com.mysql.jdbc.Driver
        application:
          name: userservice             #user的服务名称
      mybatis:
        type-aliases-package: cn.itcast.user.pojo
        configuration:
          map-underscore-to-camel-case: true
      logging:
        level:
          cn.itcast: debug
        pattern:
          dateformat: MM-dd HH:mm:ss:SSS
      eureka:
        client:
          service-url:                 #eureka的地址信息
            defaultZone: http://127.0.0.1:10086/eureka
          
          ================>>>>>>>>>>>>>>
        application:
          name: userservice             #user的服务名称
          
      eureka:
        client:
          service-url:                 #eureka的地址信息
            defaultZone: http://127.0.0.1:10086/eureka
      ```

2. 将userservice启动多份，模拟多实例部署，但为了避免端口冲突，需要修改端口设置。

   ```java
   -Dserver.port=8082
   ```








1. 在order-service项目的启动类OrderApplication中的RestTemplate添加负载均衡注解：

   ```java
       @Bean
       @LoadBalanced
       public RestTemplate restTemplate(){
           return new RestTemplate();
       }
   ```

   