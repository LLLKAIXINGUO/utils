# springcloud  day01 服务拆分与远程服务调用

### 微服务： 微服务是一种经过良好架构设计的分布式架构方案，微服务的架构特征有；

* **单一职责：**微服务拆分粒度更小，每一个服务都对应唯一的业务能力，做到单一职责，避免重复业务开发
* **面向服务**：微服务对外暴露业务接口
* **自治**：团队独立、技术独立、数据独立、部署独立
* **隔离性强：**服务调用做好隔离、容错、降级，避免出现级联问题

**服务注册发现** ： Eureka、Nacos、Consul

**服务远程调用**：OpenFeign、Dubbo

**服务链路监控**：Zipkin、Sleuth

**统一配置管理**：SpringCloudConfig、Nacos

**统一网关路由**：SpringCloudGetway、Zuul

**流控、降级、保护**：Hystix、Sentinel



## 服务拆分的注意事项 【要根据业务模块拆分，做到单一职责，不要重复开发相同业务】

1. 不同微服务，不要重复开发相同业务
2. 微服务数据独立，不要访问其它微服务的数据库
3. 微服务可以将自己的业务暴漏为接口。供其它微服务调用

## 微服务远程调用

#### 基于RestTemplate实现的远程调用

1. 步骤一：【在order-service的OrderApplication中注册RestTemplate】

2. ```java
   在配置类中加入   【加入到OrderApplication中】
   /**
    * 创建RestTemplate并注入Spring容器
    * @return
    */
   @Bean
   public RestTemplate restTemplate(){
       return new RestTemplate();
   }
   ```

3. 在orderService中注入@autoried  RestTemplate

4. 在查询订单后加入利用RestTemplate发起Http请求，查询用户

   ```java
   @Service
   public class OrderService {
   
       @Autowired
       private OrderMapper orderMapper;
   
       @Autowired
       private RestTemplate restTemplate;
   
       public Order queryOrderById(Long orderId) {
           // 1.查询订单
           Order order = orderMapper.findById(orderId);
           //2.利用restTemplate发出http请求查询用户信息
           //2.1url路径
           String url="http://localhost:8081/user/"+order.getUserId();
           //2.2发出http请求，实现远程调用
           User user = restTemplate.getForObject(url, User.class);
           //3.封装user到order
           // 4.返回
           order.setUser(user);
           return order;
       }
   ```



## 服务远程调用：

#### 提供者与消费者

* 服务提供者：一次业务中，被其它微服务调用的服务。（提供接口给其它微服务）
* 服务消费者：一次业务中，调用其它微服务的服务。（调用其它微服务提供的接口）
* 消费者和提供者的概念是相对的 ，一个服务既可以是消费者也可以是提供者



