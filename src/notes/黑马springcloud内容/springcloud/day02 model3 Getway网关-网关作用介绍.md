# day02 model3 Getway网关-网关作用介绍

#### 为什么需要网关：

###### 网关功能：

* 身份认证和权限校验
* 服务路由、负载均衡
* 请求限流



##### 网关的技术实现

在springcloud中网关的实现包括两种：

* gateway
* zuul

Zuul是基于Servlet的实现，属于阻塞式编程。而SpringCloud**Gateway**则是基于Spring5中提供的WebFlux，属于响应式编程的实现，具备更好的性能

#### 搭建网关服务

1. 创建新的moudle,引入SpringCloudGateway的依赖和nacos的服务发现依赖

```xml
<!--网关依赖-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
        </dependency>
        <!--nacos服务发现依赖-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
```

2. 让这个服务启动起来，创建springApplication类 加入`@SpringBootApplication`注解，创建main方法
3. 编写路由配置及nacos地址

```yaml
server:
  port: 10010
spring:
  application:
    name: gateway
  cloud:
    nacos:
      server-addr: localhost:8848  #nacos地址
    gateway:
      routes:
        - id: user-service   #路由标识，必须唯一
          uri: lb://userservice  # 路由的目标地址  lb表示负载均衡的缩写
          predicates: #路由断言，判断请求是否符合规则
            - Path=/user/**  #路径断言，判断路径是否以/user开头，如果是则符合
        - id: order-service
          uri: lb://orderservice
          predicates: #路由断言，判断请求是否符合规则
            - Path=/order/** #路径断言，判断路径是否以/order开头，如果是则符合
```

4. 启动这个服务， 访问  10010端口       `localhost:10010/user/1`

#### 路由过滤器  GatewayFilter

GatewayFilter是网关中提供的一种过滤器，可以对进入网关的请求和微服务返回的响应做处理：