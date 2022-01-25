# day03、Ribbon负载均衡

### IRule接口，决定了负载均衡的策略 

![1639964421493](G:%5CTYPORA%E6%96%87%E6%A1%A3%E5%A1%AB%E5%85%85%5C%E9%BB%91%E9%A9%ACspringcloud%E5%86%85%E5%AE%B9%5Cspringcloud%5Cday01%20model3%20Ribbon%E8%B4%9F%E8%BD%BD%E5%9D%87%E8%A1%A1.assets%5C1639964421493-1640051880272.png)

#### 负载均衡规则的调整

通过定义IRule实现可以修改负载均衡规则，有两种方式：

1. 代码方式实现：在order-service中的OrderApplication类中，定义一个新的IRule

```java
@Bean
public IRule randomRule(){   
    return new RandomRule();
}
```

2. 配置文件方式：在order-service的application.yaml文件中，添加新的配置也可以修改规则：（不会有提示 在最后一行的最开始处进行添加）

   ```yaml
   userservice:
     ribbon:
       NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule    #负载均衡规则
   ```

   ```yaml
   #####在集群内部随机轮询
   userservice: #要配置的微服务名称
     ribbon:
       NFLoadBalancerRuleClassName: com.alibaba.cloud.nacos.ribbon.NacosRule
   ```
   
   

#### Ribbon负责均衡策略的调整

##### 饥饿加载

Ribbon默认是采用懒加载，级第一次访问时才会去创建LoadBalanceClient,请求时间会很长。

而饥饿加载则会在项目启动时创建，降低第一次访问的耗时，通过下面配置开启饥饿加载：

```yaml
ribbon:
  eager-load:
    enabled: true    #开启饥饿加载
    clients: userservice #指定饥饿加载是对哪个服务的
```



