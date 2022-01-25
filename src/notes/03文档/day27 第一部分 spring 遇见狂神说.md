# day27 第一部分 spring 遇见狂神说

#### Bean的作用域

singleton   单例模式（spring默认机制）

<bean id="address" class="com.lym.pojo.Address" scope="singleton"/>

prototype  原型模式：每次从容器中getbean的时候，都会产生一个新对象

```xml
<bean id="address" class="com.lym.pojo.Address" scope="prototype"/>
```

其余的  request、sesson、application这些个只能在web开发中使用到



#### 自动装配bean

* 自动装配是spring满足bean依赖的一种方式
* spring会在上下文自动寻找，并自动给bean装配属性



在spring中有三种装配的方式：

1. 在xml中显示的配置
2. 在java中显示配置
3. **隐式的自动装配bean**【重点】



#### 测试自动装配

```xml
    <bean id="cat" class="com.lym.pojo.Cat"/>
    <bean id="dog" class="com.lym.pojo.Dog"/>
    <bean id="people" class="com.lym.pojo.People" autowire="byName">
        <property name="name" value="lym"/>
<!--        <property name="cat" ref="cat"/>-->
<!--        <property name="dog" ref="dog"/>-->
<!--        使用自动装配-->
      </bean>
```

autowire="byName" 

byName:会自动在容器上下文中查找，和自己对象set方法后面的值对应的bean 的id

byType:会自动在容器上下文中查找，和自己对象属性类型相同的bean

总结：

* byName的时候，需要保证所有bean的id唯一，并且这个bean需要和自动注入的属性的set方法的值一致
* byType的时候，需要保证所有bean的class唯一，并且这个bean需要和自动注入的属性的类型一致



#### **使用注解自动装配**

jdk1.5支持的注解，spring2.5开始支持的注解

要使用注解，  须知

1. 导入约束:    context约束

   1. ```xml
      <?xml version="1.0" encoding="UTF-8"?>
      <beans xmlns="http://www.springframework.org/schema/beans"    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"    xmlns:context="http://www.springframework.org/schema/context"    xsi:schemaLocation="http://www.springframework.org/schema/beans        https://www.springframework.org/schema/beans/spring-beans.xsd        http://www.springframework.org/schema/context        https://www.springframework.org/schema/context/spring-context.xsd">    
          <context:annotation-config/> 
      </beans>
      ```

      

2. 配置注解的支持 ：开启注解的支持==<<context:annotation-config/>>== 

@Autowired在属性上直接使用就行



如果@Autowired自动装配的环境比较复杂，自动装配无法通过一个注解来完成的时候，可以使用@Qualifier("")配合使用,指定一个唯一的bean对象



@Nullable   字段标记了这个注解，说明这个字段可以为null



@Resource和@Autowired的区别

* 都是用来自动装配的，都可以放在属性字段上

* @Autowired通过byType的方式实现，而且必须要求这个对象存在【常用】

* @Resource默认通过byname的方式实现，如果找不到名字，则通过byType实现，如果两个都找不到的情况下，就报错！

* 执行顺序不同：@Autowired默认通过byType的方式实现  ,,,    @Resource默认通过byname的方式实现

  



**使用注解开发**

在spring4之后，要使用注解开发，必须要保证aop的包导入了

使用注解需要导入context约束，增加注解的支持 



1. bean
2. 属性如何注入

```xml
<context:component-scan base-package="com.lym.pojo"/>
指定哟啊扫描的包，//扫描pojo包下的注解
component-scan扫描所有的组件
```

在配置这个之后，将@Component 注解放在类上，就表示这个类被spring管理了，就是bean

3. 衍生的注解

   @Component有几个衍生注解，我们在web开发中，会按照mvc三层架构分层

    * dao      【@Repository】
    * service 【@Service】
    * controller  【@Controller 】
    * 这四个注解功能都是一样的，都是代表将某个类注册到Sprign中，装配Bean

4. 自动装配配置

5. 作用域

6. 小结   xml与注解

   * xml更加万能，适用于任何场合，维护简单方便
   * 注解，不是自己的类使用不了，维护相对复杂

   最佳实践：

   * xml用来管理bean
   * 注解只负责完成属性的注入
   * 我们在使用的过程中，只需要注意一个问题，必须让注解生效，就是必须要开启注解的支持

   

## 使用java的方式配置Spring

我们现在要完全不适用spring的xml配置文件了，全权交给java来做

javaconfig是spring的一个子项目，在spring4之后，它变成了核心功能



创建maven项目后，不导入配置文件

创建user类 private string name   getterandsetter  tostring方法

创建config包  ，创建myconfig类  在类上加上@ Configuration注解





创建Rent接口  public void rent();

创建Host类  实现Rent接口        sout("房东出租房子")

创建代理类    Proxy

```java
private Host host;
public Proxy() {
}
public Proxy(Host host) {
    this.host = host;
}
@Override
public void rent() {
    host.rent();
}
```

创建Client客户类  

```java
Host host = new Host();
Proxy proxy = new Proxy(host);
proxy.rent();
```

代理模式： 

代理模式是SpringAOP的底层

**代理模式的好处：**

* 可以使真实角色的操作更加纯粹，不用去关注一些公共的业务
* 公共业务就交给代理角色，实现了业务的分工
* 公共业务发生扩展的时候，方便集中管理

**代理角色的缺点：**

* 一个真实角色就会产生一个代理角色，代码量会翻倍 ~开发效率会变低【解决方案：动态代理】



代理模式的分类：

1. 静态代理
2. 动态代理

角色分析

* 抽象对象：一般会使用接口或者抽象类来解决
* 真实对象：被代理的角色
* 代理角色：代理真实角色，代理真实角色后，我们一般会做一些附属操作
* 客户：访问代理对象

代理步骤：

1. 接口 

   ```java
   public interface Rent {
       public void rent();
   }
   ```

2. 真实对象

   ```java
   public class Host implements Rent{
       @Override
       public void rent() {
           System.out.println("房东出租房子");
       }
   }
   ```

3. 代理角色

   ```java
   public class Proxy implements Rent{
       private Host host;
   
       public Proxy() {
       }
   
       public Proxy(Host host) {
           this.host = host;
       }
   
       @Override
       public void rent() {
           this.show();
           host.rent();
           this.fare();
           this.hetong();
       }
       public void show(){
           System.out.println("中介带租赁人去看房子");
       }
       public void fare(){
           System.out.println("中介抽成");
       }
       public void hetong(){
           System.out.println("中介签合同");
       }
   }
   ```

4. 客户端访问代理角色

   ```java
   public class Client {
       public static void main(String[] args) {
           Host host = new Host();
           Proxy proxy = new Proxy(host);
           proxy.rent();
       }
   }
   ```

**动态代理**：    动态代理的底层【反射】

* 动态代理和静态代理角色一样

* 动态代理的代理类是动态生成的，不是我们直接写好的

* 动态代理分为两大类  ：  基于接口的动态代理，基于类的动态代理

  * 基于接口的-----JDK动态代理 【接下来使用的】
  * 基于类：    cglib

  需要了解两个类      Proxy 【代理】、InvocationHandler【调用处理程序】

# AOP

面向切面编程

AOP在Sprign中的作用

==提供声明式事务，允许用户自定义切面==

* 横切关注点：阔约应用程序多个模块的方法或功能。即是。与我们业务逻辑无关的，但是需要我们关注的部分，就是横切关注点。如：日志、安全、缓存、事务等
* 切面：横切关注点  被模块化 的特殊对象。即，它是一个类
* 通知：切面必须要完成的工作。即，他是类中的一个方法
* 目标：被通知的对象
* 代理：向目标对象应用通知之后创建的对象
*  切入点：切面通知执行的“地点”的定义 
* 连接点：与切入点匹配的执行点

```xml
AOP织入的包
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.7</version>
</dependency>
```



**使用注解方式实现：** aop

自定义类

```java
@Aspect
public class AnnotationAop {
    @Before("execution(* com.lym.service.UserService.*(..))")
    public void before(){
        System.out.println("方法执行前");
    }
}
```

```xml
注册bean
<bean id="userService" class="com.lym.service.Impl.UserServiceImpl"/>
<bean id="annotationPoinCut" class="com.lym.diy.AnnotationAop"/>
```

```xml
开启注解支持
<aop:aspectj-autoproxy/>
```



# 整合Mybatis

步骤：

1. 导入相关的jar包
   * junit
   * mybatis
   * mysql数据库
   * spring相关的 
   * aop织入
   * myBatis-spring
2. 编写配置文件
3. 测试



回忆mybatis

1. 编写实体类
2. 编写配置文件 工具类
3. 编写接口
4. 编写Mapper.xml文件
5. 编写测试类



## spring整合mybatis

##### 第一种方式

1. 创建applicationContext
2. 在其中配置数据源   以及  sqlsessionfactory
3. sqlSessionTemplate
4. 需要给接口加实现类
5. 测试



##### 第二种方式



### 声明式事务

回顾事务

1. 把一组业务当成一个来做，要么都成功，要么都失败
2. 事务在项目开发中，十分的重要，涉及到数据的一致性问题，不能马虎
3. 确保完整性和一致性

事务的acid原则  ：  原子性  一致性   隔离性（多个业务可能操作同一个资源，防止数据损坏）   持久性（数据一旦提交，无论系统发生什么问题，结果都不会被影响）





##### spring中的事务管理

* 声明式事务：AOP横切进去
* 编程式事务：需要在代码中，进行事务的管理



* ##### 如果不配置事务，  可能存在数据提交不一致的情况

