# day02 model2 http基于feign远程调用

## Feign替代RestTemplate

### RestTemplate方式调用存在的问题：

* 代码可读性差，编程体验不统一
* 参数复杂URL难以维护

Feign是一个声明式的http客户端，其作用就是帮助我们优雅的实现http请求的发送，解决上面提到的问题



#### 定义和使用Feign客户端

1. 引入依赖：      找到order-service的依赖加入

   ```xml
   <!--        feign客户端依赖-->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-openfeign</artifactId>
           </dependency>
   ```

2. 开启服务端的依赖  ： 在userservice的controller上加上   @EnableFeignClients

3. 写UserClient类

```java
@FeignClient("userservice")
public interface UserClient {

    @GetMapping("/user/{id}")
    User findId(@PathVariable("id") Long id);
}
```

4. 用feign远程调用

```java
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

//    @Autowired
//    private RestTemplate restTemplate;
    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
//        //2.利用resttemplate发起http请求
//        String url = "http://userservice/user/"+order.getUserId();
//        User user = restTemplate.getForObject(url, User.class);
        User user = userClient.findId(order.getUserId());
        order.setUser(user);
//         4.返回
        return order;
    }
}
```



##### Feign的使用步骤：

1. 引入依赖
2. 添加@EnableFeignClients注解
3. 编写FeignClient接口
4. 使用FeignClient中定义的方法代替RestTemplate



### 自定义Feign的配置

Feign运行自定义配置来覆盖默认配置，可以修改的配置如下：![1640592466846](G:%5CTYPORA%E6%96%87%E6%A1%A3%E5%A1%AB%E5%85%85%5C%E9%BB%91%E9%A9%ACspringcloud%E5%86%85%E5%AE%B9%5Cspringcloud%5Cday02%20model2%20http%E5%9F%BA%E4%BA%8Efeign%E8%BF%9C%E7%A8%8B%E8%B0%83%E7%94%A8.assets%5C1640592466846.png)

###### 配置Feign日志有两种方式：

**方式一：配置文件方式**

* 全局配置      在order-service中配置

  ```yaml
  feign:
    client:
      config:
        default:
          loggerLevel: FULL
  ```

* 局部生效：

```yaml
feign:
  client:
    config:
      userservice:
        loggerLevel: FULL
```

**方式二：java代码方式，需要先声明一个Bean**

* 1.创建一个配置类，需要先声明一个Bean

```java
public class DefaultFeign {
    @Bean
    public Logger.Level logLevel(){
        return Logger.Level.FULL;
    }
}
```

* 2.而后如果是全局配置，则把它放到@EnableFeignClients这个注解中：

```java
@EnableFeignClients(defaultConfiguration =  DefaultFeign.class)
```

* 如果是局部配置，则把它放到@FeignClient这个注解中：

```java
@FeignClient(value = "userservice",configuration = DefaultFeign.class)
```



### Feign的性能优化

Feign底层的客户端实现：

* URLConnection: 默认实现，不支持连接池
* Apache HttpClient:  支持连接池
* OKHttp:  支持连接池



**因此，优化Feign的性能主要包括：**

1. 使用连接池代替默认的URLConnection
2. 日志级别，最好用basic或none



**Feeign的性能优化--连接池配置**

Feign添加HttpClient的支持：

引入依赖：

```xml
<!--            Feign添加HttpClient的支持-->
<!--            httpClient的依赖-->
            <dependency>
                <groupId>io.github.openfeign</groupId>
                <artifactId>feign-httpclient</artifactId>
                <version>11.8</version>
            </dependency>
```

加入配置：

```yaml
feign:
  httpclient:
    enabled: true     #支持HttpClient的开关
    max-connections: 200        #最大连接数
    max-connections-per-route: 50 #单个路径的最大连接数
```



**Feign的优化：**

1. 日志级别尽量用basic
2. 使用HttpClient或OKHttp代替URLConnection
   1. 引入feign-httpClient依赖
   2. 配置文件开启httpclient功能，设置连接池参数



**Feign的最佳实践**

**==这种方式紧耦合==**

**方式一（继承）**：给消费者的FeignClient和提供者的controller定义统一的父接口作为标准                                                                                       

**方式二（抽取）：**将FeignClient抽取为独立模块，并且把接口有关的POJO、默认的Feign配置都放到这个模块中，提供给所有消费者使用

**总结：**

1. 让controller和FeignClient继承同一接口
2. 将FeignClient、POJO、Feign的默认配置都定义到一个项目中，供所有消费者使用

**实现最佳实践方式二的步骤如下**

1. 首先创建一个module,命名为feign-api，然后引入Feign的starter依赖

2. 将order-service中编写的UserClient、User、DefaultFeignConfiguration都复制到feign-api项目中

3. 在order-service中引入feign-api的依赖

4. 修改order-service中的所有与上述三个组件有关的import部分，改成导入feign-api中的包

5. 重启测试

   此时报错       feign.clients不在springbootApplication的扫描包范围

**Field userClient in cn.itcast.order.service.OrderService required a bean of type 'cn.itcast.feign.clients.UserClient' that could not be found.**

当==定义的FeignClient不在SpringBootApplication的扫描包范围==时，这些FeignClient无法使用。有两种解决方案：

**方式一：**指定FeignClient所在的包     

```java
@EnableFeignClients(basePackages = "cn.itcast.feign.clients")
```

**方式二：**指定FeignClient字节码

```java
@EnableFeignClients(clients = {UserClient.class})
```

