# day26 第一部分 spring 遇见狂神说

官方下载地址：https://repo.spring.io/release/org/springframework/spring

https://docs.spring.io/spring-framework/docs/5.2.0.RELEASE/spring-framework-reference/core.html#spring-core

# Spring Framework

Spring是一个轻量级的控制反转（**IoC**）和面向切面（**AOP**）的容器框架

**Spring理念**：使现有的技术更加容易使用，整合了现有的技术框架

SSM：springMvc+Spring+Mybatis

导包：

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.3.10</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.3.10</version>
</dependency>
```

**优点**

* spring是一个开源的免费框架（容器）
* spring是一个轻量级、非入侵式的框架
* 控制反转(IOC),面向切面（AOP）
* 支持事务的处理,对框架整合的支持



spring  学习路线  构建一切（spring boot）---》协调一切(spring cloud)----》连接一切(spring cloud data flow)

* spring boot
  * 一个快速开发的脚手架
  * 基于SpringBoot可以快速的开发单个微服务
  * 约定大于配置
* spring cloud
  * springcloud是基于springboot实现的



现在大多数公司都在使用springboot进行快速开发，学习springboot的前提 是spring和springmvc



spring的弊端是：发展太多，使得配置十分繁琐



## IOC**理论**

IOC是一种设计思想        DI依赖注入，是实现ioc的一种方法

ioc是spring框架的核心内容，使用多种方式完美的实现了Ioc，可以使用XML配置，也可以使用注解，新版本的Spring也可以零配置实现Ioc

 spring容器在初始化时先读取配置文件，根据配置文件或元数据创建与组织对象存入容器中，程序使用时再从Ioc容器中取出需要的对象



**控制反转是一种通过描述（XML或注解）并通过第三方去生产或获取特定对象的方式。在Spring中实现控制反转的是IOC容器，其实现方法是依赖注入（DI）**





### **HelloSpring**

1. 创建项目，导入依赖
2. 创建pojo实体类 Hello
3. 创建spring的核心配置文件， applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- 使用spring来创建对象，在Spring这些都称为Bean-->
    
    <!--    类型 变量名 = new 类型（）;-->
<!--    Hello hello = new Hello();-->
<!--    id相当于 变量名  class相当于new的对象  -->
<!--    property 相当于给对象中的属性设置一个值-->
    <bean id="hello" class="com.lym.pojo.Hello">
        <property name="str" value="String"/>
     </bean>
    这个过程就叫控制反转
</beans>
```

4. 实例化容器

   ```java
   ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
   ```

5. 获取容器的bean

   ```java
   context.getBean("userServiceImpl");    .var
   ```

6. 实现方法   

使用bean创建对象的过程  就叫控制反转

控制：谁来控制对象的创建，传统的应用程序的对象是由程序本身控制创建的，使用spring后，对象是由Spring来创建的

反转：程序本身不创建对象，而变成被动的接收对象

依赖注入：就是利用set方法来进行注入的 意思是{将该类的set方法去掉，程序就直接报错了}

ioc是一种编程思想，由主动的编程编程被动的接收

所谓的IOC，即一句话：对象由Spring来创建，管理，装配



bean的property的 属性值

ref:引用spring容器中创建好的对象

value:具体的值，基本数据类型



## IOC创建对象的方式：

1. 使用无参构造创建对象，默认！

2. 假设我们要使用有参构造创建对象

   1. 下标赋值

      ```xml
      <bean id="user" class="com.lym.pojo.User">
          <constructor-arg index="0" value="luym"/>
       </bean>
      ```

   2. 引用类型赋值

      ```xml
      <!--    不建议使用该种方法，因为一旦存在多个同一类型的值就会出问题-->
          <bean id="user" class="com.lym.pojo.User">
              <constructor-arg type="java.lang.String" value="luyuanmign"/>
          </bean>
      ```

   3. 直接通过参数名

      ```xml
      <!--    第三种，直接通过参数名-->
          <bean id="user" class="com.lym.pojo.User">
              <constructor-arg name="name" value="xiaozhang"/>
          </bean>
      ```



**总计：**在配置文件加载的时候，容器中管理的对象就已经初始化了



# spring配置

### 别名

```xml
<alias name="user" alias="userNew"/>
```

### bean的配置

```xml
<!-- id对象名     class对象的全限定类名  name也是起别名  -->
<bean id="user1" class="com.lym.pojo.User" name="user2">
    <constructor-arg name="name" value="lllll"/>
</bean>
```

### import配置

这个import，一般用于团队开发，它可以将多个配置文件，导入合并成为一个。

假设，现在项目由多个人开发，这几个人负责不同的类的开发，不同的类需要注册在不同的bean中，我们可以使用import将所有人的【context】.xml合并为一个总的

**使用的时候直接使用总的就可以了**





# 依赖注入 

1. 构造器注入
2. Set方式注入【重点】
3. 扩展方式的注入



* **依赖**：bean对象的创建依赖于容器
* **注入**：bean对象中的所有属性，由容器来注入



#### 环境搭建

* 复杂对象

* ```java
  private String address; //getter and setter
  ```

* 真实对象

* ```java
  private String name;
  private Address address;
  private String[] books;
  private List<String> hobbys;
  private Map<String,String> cards;
  private Set<String> games;
  private String wife;
  private Properties info;  //getter and setter
  ```

* 配置文件 applicationContxt.xml

* ```xml
      <bean id="address" class="com.lym.pojo.Address"/>
      <bean id="student" class="com.lym.pojo.Student">
  <!--        第一种，普通值注入，value-->
          <property name="name" value="lym"/>
  <!--        第二种，Bean注入，ref-->
          <property name="address" ref="address"/>
          <property name="books">
              <array>
                  <value>红楼梦</value>
                  <value>西游记</value>
                  <value>水浒传</value>
                  <value>三国演义</value>
              </array>
          </property>
          <property name="hobbys">
              <list>
                  <value>听歌</value>
                  <value>敲代码</value>
                  <value>看电影</value>
              </list>
          </property>
          <property name="cards">
              <map>
                  <entry key="身份证" value="25010001"/>
                  <entry key="银行卡" value="201010002"/>
                  <entry key="学生卡" value="201715010003"/>
              </map>
          </property>
          <property name="games">
              <set>
                  <value>lol</value>
                  <value>nishuihan</value>
                  <value>王者</value>
                  <value>恰鸡</value>
              </set>
          </property>
          <property name="wife">
              <null/>
          </property>
          <property name="info">
              <props>
                  <prop key="宿舍号">111</prop>
                  <prop key="性别">男性</prop>
                  <prop key="名字">root</prop>
                  <prop key="密码">root</prop>
              </props>
          </property>
       </bean>
  ```

* 测试类

* ```java
  ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
  Student student = (Student) context.getBean("student");
  System.out.println(student.toString());
  System.out.println(student.getName());
  System.out.println(student.getAddress().getAddress());
  System.out.println(Arrays.toString(student.getBooks()));
  System.out.println(student.getCards());
  System.out.println(student.getGames());
  System.out.println(student.getInfo());
  System.out.println(student.getWife());
  System.out.println(student.getHobbys());
  ```

Student{name='lym', address=Address{address='洛阳'}, books=[红楼梦, 西游记, 水浒传, 三国演义], hobbys=[听歌, 敲代码, 看电影], cards={身份证=25010001, 银行卡=201010002, 学生卡=201715010003}, games=[lol, nishuihan, 王者, 恰鸡], wife='null', info={性别=男性, 宿舍号=111, 名字=root, 密码=root}}
lym
洛阳
[红楼梦, 西游记, 水浒传, 三国演义]
{身份证=25010001, 银行卡=201010002, 学生卡=201715010003}
[lol, nishuihan, 王者, 恰鸡]
{性别=男性, 宿舍号=111, 名字=root, 密码=root}
null
[听歌, 敲代码, 看电影]





**p命名空间**

第三方约束`xmlns:p="http://www.springframework.org/schema/p"`

**c命名空间**  

