# day29 springmvc 遇见狂神说



* 在java中生成json对象的方式



### jackson解析工具

1. 首先导包jackson

```xml
 <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.13.0</version>
```



方法或类上只要加了@ResponseBody  它就不会走视图解析器了，会直接返回一个字符串

```java
@Controller
public class JsonController {
    @ResponseBody
    @RequestMapping(value = "/j1")
    public String test01() throws JsonProcessingException {
        User user = new User("张三",5,"男");
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(user);
        System.out.println(s);
        return s;
    }
}  //出现中文乱码问题
```

```java
@Controller
public class JsonController {
    @ResponseBody
    @RequestMapping(value = "/j1",produces ="application/json;charset=utf-8" )
    public String test01() throws JsonProcessingException {
        User user = new User("张三",5,"男");
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(user);
        System.out.println(s);
        return s;
    }
}  //解决中文乱码问题     @RequestMapping  里的produces参数
```

2. springmvc统一解决乱码问题

```xml
<mvc:annotation-driven>
    <mvc:message-converters>
        <bean class="org.springframework.http.converter.StringHttpMessageConverter">
            <constructor-arg value="UTF-8"/>
        </bean>
        <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
            <property name="objectMapper">
                <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                    <property name="failOnEmptyBeans" value="false"/>
                </bean>
            </property>
        </bean>
    </mvc:message-converters>
</mvc:annotation-driven>
```

3. new  一个

   ```java
   @Controller
   public class JsonController {
       @ResponseBody
       @RequestMapping(value = "/j1")
       public String test01() throws JsonProcessingException {
           User user = new User("张三",5,"男");
           ObjectMapper mapper = new ObjectMapper();
           String s = mapper.writeValueAsString(user);
           System.out.println(s);
           return s;
       }
   }
   ```





# **整合ssm**

1. 需求分析
2. 设计数据库
3. 业务
4. 前端界面



1. 创建了数据库ssmday18      books 表
   * 90%的业务是CRUD
2. 新搭建一个maven项目   （导入依赖）

 （静态资源导出问题）

依赖：junit\数据库驱动\连接池、servlet  jsp mybatis  mybatis-spring

1. ```xml
   <dependencies>
           <!--Junit-->
           <dependency>
               <groupId>junit</groupId>
               <artifactId>junit</artifactId>
               <version>4.13</version>
           </dependency>
           <!--数据库驱动-->
           <dependency>
               <groupId>mysql</groupId>
               <artifactId>mysql-connector-java</artifactId>
               <version>5.1.48</version>
           </dependency>
           <!--连接池-->
           <dependency>
               <groupId>com.mchange</groupId>
               <artifactId>c3p0</artifactId>
               <version>0.9.5.2</version>
           </dependency>
           <!-- https://mvnrepository.com/artifact/javax.servlet/servlet-api -->
           <dependency>
               <groupId>javax.servlet</groupId>
               <artifactId>servlet-api</artifactId>
               <version>2.5</version>
           </dependency>
   
           <!-- https://mvnrepository.com/artifact/javax.servlet.jsp/javax.servlet.jsp-api -->
           <dependency>
               <groupId>javax.servlet.jsp</groupId>
               <artifactId>jsp-api</artifactId>
               <version>2.2</version>
           </dependency>
   
   <!--        JSTL-->
           <dependency>
               <groupId>javax.servlet</groupId>
               <artifactId>jstl</artifactId>
               <version>1.2</version>
           </dependency>
   <!--        mybatis-->
           <dependency>
               <groupId>org.mybatis</groupId>
               <artifactId>mybatis</artifactId>
               <version>3.5.2</version>
           </dependency>
           <!--mybatis-spring-->
           <dependency>
               <groupId>org.mybatis</groupId>
               <artifactId>mybatis-spring</artifactId>
               <version>2.0.2</version>
           </dependency>
           <!--spring-->
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-webmvc</artifactId>
               <version>5.1.9.RELEASE</version>
           </dependency>
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-jdbc</artifactId>
               <version>5.1.9.RELEASE</version>
           </dependency>
        <!--事务支持-->
       <dependency>
               <groupId>org.aspectj</groupId>
               <artifactId>aspectjweaver</artifactId>
               <version>1.9.7</version>
           </dependency>
       </dependencies>
   ```
   
   防止资源导出失败
   
   ```xml
   <!--在build中配置resources，来防止我们资源导出失败的问题-->
       <build>
           <resources>
               <resource>
                   <directory>src/main/resources</directory>
                   <includes>
                       <include>**/*.properties</include>
                       <include>**/*.xml</include>
                   </includes>
                   <filtering>true</filtering>
               </resource>
               <resource>
                   <directory>src/main/java</directory>
                   <includes>
                       <include>**/*.properties</include>
                       <include>**/*.xml</include>
                   </includes>
                   <filtering>true</filtering>
               </resource>
           </resources>
       </build>
   ```
   
   3. 创建目录结构      pojo\dao\service\controller
   
   4. 在resources下创建  mybatis-config.xml   和 applicationContext.xml
   
   5. ```xml
      mybatis-config.xml  mybatis的核心配置文件
      <?xml version="1.0" encoding="UTF-8"?>
      <!DOCTYPE configuration
              PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
              "http://mybatis.org/dtd/mybatis-3-config.dtd">
      <configuration>
         
      
      </configuration>
      ```
   
6. ```xml
    applicationContext.xml    spring的核心配置文件
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"       xsi:schemaLocation="http://www.springframework.org/schema/beans
          https://www.springframework.org/schema/beans/spring-beans.xsd ">
       
   </beans>
   ```

7. 创建db.properties

8. ```properties
   jdbc.driver=com.mysql.jdbc.Driver
   jdbc.url=jdbc:mysql://localhost:3306/ssmday18?useSSL=false&useUnicode=true&characterEncoding=utf8
   jdbc.username=root
   jdbc.password=1234
   ```

9. 写pojo、 dao层以及service层代码    mapper.xml------->接口和mapperxml写完了以后，把这个mapper绑定到mybatis-config中
10. mybatis完成，创建spring-dao.xml  

```xml
spring-dao.xml  整合dao层    dao层的spring核心配置文件 
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       https://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       ">
<!--    2.连接池-->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <!-- 配置连接池属性 -->
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
        <!-- c3p0连接池的私有属性 -->
        <property name="maxPoolSize" value="30"/>
        <property name="minPoolSize" value="10"/>
        <!-- 关闭连接后不自动commit -->
        <property name="autoCommitOnClose" value="false"/>
        <!-- 获取连接超时时间 -->
        <property name="checkoutTimeout" value="10000"/>
        <!-- 当获取连接失败重试次数 -->
        <property name="acquireRetryAttempts" value="2"/>
    </bean>
<!--    3.sqlsessionfactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
<!--        绑定mybatis的配置文件-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
    </bean>
    <!--    配置dao接口扫描包，动态的实现了Dao接口可以注入到Spring容器中-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
<!--        注入sqlsessionfactory-->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
<!--        扫描要扫描的dao包-->
        <property name="basePackage" value="com.lym.dao"/>
    </bean>
</beans>
```

```xml
spring-service.xml  
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans        
       https://www.springframework.org/schema/beans/spring-beans.xsd        
       http://www.springframework.org/schema/context        
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/aop        
       https://www.springframework.org/schema/aop/spring-aop.xsd">

<!--    1.扫描service下的包-->
    <context:component-scan base-package="com.lym.service"/>
<!--    2.将我们的所有业务类，注入到spring,可以通过配置或注解-->
    <bean id="BookServiceImpl" class="com.lym.service.Impl.BooksServiceImpl">
        <property name="booksMapper" ref="booksMapper"/>
    </bean>
<!--    3.声明式事务配置-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
<!--        注入数据源-->
        <property name="dataSource" ref="dataSource"/>
    </bean>
<!--    4.aop事务支持-->
</beans>
```

```xml
增加web的支持 
web.xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
<!--    DispatcherServlet-->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:applicationContext.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
<!--    乱码过滤-->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

<!--    session过期时间,15分钟-->
    <session-config>
        <session-timeout>15</session-timeout>
    </session-config>
</web-app>
```

```xml
spring-mvc.xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       https://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/mvc
       http://www.springframework.org/schema/mvc/spring-mvc.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd">

<!--    1.注解驱动-->
    <mvc:annotation-driven/>

<!--    2.静态资源过滤-->
    <mvc:default-servlet-handler/>
<!--    3.扫描包-->
    <context:component-scan base-package="com.lym.controller"/>
<!--    4.视图解析器-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
</beans>
```

```java
@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    @Qualifier("BookServiceImpl")
    private BooksService booksService;
    //查询全部的书籍,并且返回一个书籍展示页面
    @RequestMapping("/allBook")
    public String list(Model model){
        List<Books> books = booksService.queryAllBook();
        model.addAttribute("list",books);
        return "allBook";
    }
}
```

此时运行的话，会在tomcat报错。  点击项目的project structure   

---->Artifacts 然后把lib加载进去



**运行后出来页面，点击超链接跳转，出现500错误 ------排错思路**

**问题：** bean不存在

1. 查看这个bean是否注入成功        找到其配置文件，看是否可以跳转到    ok

2. junit单元测试，看代码是否能查出来结果 (MyTest)       ok

3. 证明问题并不是出现在底层，是Spring出了问题

4. 在controller中手动注入service  去new一个service 

5. 发现错误是springmvc中没有注入到service的bean   在web.xml中

   ```xml
   <init-param>
       <param-name>contextConfigLocation</param-name>
       这里应当使用的是applicationContext.xml，而不是SpringMVC.xml
       <param-value>classpath:applicationContext.xml</param-value>
   </init-param>
   ```



**框架搭好了，开始美化页面，以及写功能模块**

```css
index.jsp页面的美化功能
<style>
  h3{
    width: 180px;
    height:30px;
    margin:100px auto;
    text-align: center;
    line-height: 30px;
    background: aquamarine;
    border-radius: 5px;
  }
  a{
      text-decoration: none;
      font-size: 18px;
  }
</style>

<a href="${pageContext.request.contextPath}/book/allBook">进入书籍展示页面</a>
```

```jsp
引入bootstrap的cdn或者将其下载下来，放入资源文件
<%--    使用bootstrap美化界面--%>
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
```



出现的问题是：提交了修改的sql请求，但是修改失败，初步考虑是因为事务问题，配置完了事务以后，依旧失败、

```xml
spring-service中配置事务
<!--    4.aop事务支持-->
<!--    结合aop实现事务的织入-->
<!--    配置事务的通知-->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
<!--        给哪些方法配置事务-->
<!--        配置事务的传播性-->
        <tx:attributes>
            <tx:method name="add" propagation="REQUIRED"/>
            <tx:method name="delete" propagation="REQUIRED"/>
            <tx:method name="update" propagation="REQUIRED"/>
            <tx:method name="query" read-only="true"/>
            <tx:method name="*" propagation="REQUIRED"/>
        </tx:attributes>
    </tx:advice>
    <aop:config>
        <aop:pointcut id="txPointCut" expression="execution(* com.lym.dao.*.*(..))"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointCut"/>
    </aop:config>
```

看到日志中sql语句的id是0  所以是因为修改时候没有能获取到bookid



**使用隐藏域在修改的时候提交id**



加入  根据书名查询详情页面



访问流程是： 客户--》controller---》service----》dao--->数据库

最优开发步骤

分析后从dao层开始修改  





**查询单条数据，没有显示出来，  经查找原因是        jstl表达式的 错误 其中设置了begin属性，begin设置值为1，所以导致，list集合中从0开始**
