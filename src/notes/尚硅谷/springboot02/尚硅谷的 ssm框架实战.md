# 尚硅谷的 ssm框架实战

ssm框架

mysql

bootstrap

maven

分页：pagehelper

逆向工厂：Mybatis  Generator



1. 创建maven项目

2. 添加web支持

3. 引入maven依赖坐标

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
   <!--        spring面向切面编程-->
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-aspects</artifactId>
               <version>5.3.10</version>
           </dependency>
           <dependency>
               <groupId>org.mybatis</groupId>
               <artifactId>mybatis</artifactId>
               <version>3.5.6</version>
           </dependency>
   <!--        mybatis连接spring-->
           <dependency>
               <groupId>org.mybatis</groupId>
               <artifactId>mybatis-spring</artifactId>
               <version>2.0.6</version>
           </dependency>
   <!--        数据库连接池驱动-->
           <!-- https://mvnrepository.com/artifact/c3p0/c3p0 -->
           <dependency>
               <groupId>c3p0</groupId>
               <artifactId>c3p0</artifactId>
               <version>0.9.1.2</version>
           </dependency>
   
           <dependency>
               <groupId>mysql</groupId>
               <artifactId>mysql-connector-java</artifactId>
               <version>5.1.48</version>
           </dependency>
   
   <dependency>
                <dependency>
               <groupId>javax.servlet</groupId>
               <artifactId>jstl</artifactId>
               <version>1.2</version>
           </dependency>
           </dependency>
          <dependency>
               <groupId>javax.servlet</groupId>
               <artifactId>javax.servlet-api</artifactId>
               <version>3.0.1</version>
               <!--            这个包服务器上是有的所以只在生产环境上添加-->
               <scope>provided</scope>
           </dependency>
           <dependency>
               <groupId>junit</groupId>
               <artifactId>junit</artifactId>
               <version>4.13</version>
           </dependency>
   
   
   
   
   所有使用的依赖jar包
   <dependencies>
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
           <!--        spring面向切面编程-->
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-aspects</artifactId>
               <version>5.3.10</version>
           </dependency>
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-test</artifactId>
               <version>5.3.10</version>
           </dependency>
   
           <dependency>
               <groupId>org.mybatis</groupId>
               <artifactId>mybatis</artifactId>
               <version>3.5.6</version>
           </dependency>
           <!--        mybatis连接spring-->
           <dependency>
               <groupId>org.mybatis</groupId>
               <artifactId>mybatis-spring</artifactId>
               <version>2.0.6</version>
           </dependency>
           <!--        数据库连接池驱动-->
           <dependency>
               <groupId>com.mchange</groupId>
               <artifactId>c3p0</artifactId>
               <version>0.9.5.2</version>
           </dependency>
           <dependency>
               <groupId>mysql</groupId>
               <artifactId>mysql-connector-java</artifactId>
               <version>5.1.41</version>
           </dependency>
           <dependency>
               <groupId>jstl</groupId>
               <artifactId>jstl</artifactId>
               <version>1.2</version>
           </dependency>
   
           <dependency>
               <groupId>org.mybatis.generator</groupId>
               <artifactId>mybatis-generator-core</artifactId>
               <version>1.4.0</version>
           </dependency>
   
           <dependency>
               <groupId>org.projectlombok</groupId>
               <artifactId>lombok</artifactId>
               <version>1.18.20</version>
               <scope>provided</scope>
           </dependency>
           <dependency>
               <groupId>javax.servlet</groupId>
               <artifactId>javax.servlet-api</artifactId>
               <version>3.0.1</version>
               <!--            这个包服务器上是有的所以只在生产环境上添加-->
               <scope>provided</scope>
           </dependency>
   
           <dependency>
               <groupId>javax.servlet.jsp</groupId>
               <artifactId>javax.servlet.jsp-api</artifactId>
               <version>2.3.3</version>
               <scope>provided</scope>
           </dependency>
   
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-test</artifactId>
               <version>5.3.10</version>
           </dependency>
   
           <dependency>
               <groupId>junit</groupId>
               <artifactId>junit</artifactId>
               <version>4.13</version>
           </dependency>
   <!--        引入分页插件-->
   
           <dependency>
               <groupId>com.github.pagehelper</groupId>
               <artifactId>pagehelper</artifactId>
               <version>5.3.0</version>
           </dependency>
   
           <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
           <dependency>
               <groupId>com.fasterxml.jackson.core</groupId>
               <artifactId>jackson-databind</artifactId>
               <version>2.13.0</version>
           </dependency>
       </dependencies>
   
   
   ```

4. 引入bootstrap前端框架

   ```javascript
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">
   <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.js"></script>
   <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
   ```

5. 编写重要的配置文件  web.xml   spring.xml

```xml
////////////web.xml//////////////
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

<!--    启动spring容器-->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext.xml</param-value>
    </context-param>
    <listener>
<!--        在项目一启动，加载 classpath:applicationContext.xml的配置文件-->
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

<!--    springmvc的前端控制器的配置，拦截所有请求-->
    <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
<!--        <init-param>-->
<!--            <param-name>contextConfigLocation</param-name>-->
<!--            <param-value>classpath:applicationContext.xml</param-value>-->
<!--        </init-param>-->
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    
<!--    字符编码过滤器  一定是在最前面的过滤器-->
    <filter>
        <filter-name>characterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceRequestEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
        <init-param>
            <param-name>forceResponseEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>characterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

<!--    使用restful风格的URI,将 页面普通的post请求，转为指定的put  delete请求-->
    <filter>
        <filter-name>HiddenHttpMethodFilter</filter-name>
        <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>HiddenHttpMethodFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```

```xml
/////////////////springmvc的xml文件、、、、、、、
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop" xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans        
       https://www.springframework.org/schema/beans/spring-beans.xsd        
       http://www.springframework.org/schema/context        
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/aop        
       https://www.springframework.org/schema/aop/spring-aop.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">

<!--    springmvc的配置文件，包含网络跳转逻辑的控制，配置-->
    <context:component-scan base-package="com.lym" use-default-filters="false">
<!--        只扫描控制器-->
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>
<!--    配置视图解析器，方便页面返回-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

<!--    两个标准配置-->
<!--    将springmvc不能处理的交给tomcat-->
    <mvc:default-servlet-handler/>
<!--    能支持springmvc更高级的一些功能，比如JSR303的一些校验，比如快捷的ajax...映射动态请求-->
    <mvc:annotation-driven/>

</beans>
```

```xml
///////////////applicationContext.xml/////////////////////
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans        
       https://www.springframework.org/schema/beans/spring-beans.xsd        
       http://www.springframework.org/schema/context        
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/aop        
       https://www.springframework.org/schema/aop/spring-aop.xsd
       http://www.springframework.org/schema/tx
       http://www.springframework.org/schema/tx/spring-tx.xsd">
     <import resource="springMvc.xml"></import>
<!--    spring的配置文件，这里主要配置和业逻辑有关的-->
<context:component-scan base-package="com.lym"/>
<!--    数据源配置    ，，，事务控制-->
    <!--    1.关联数据库配置文件-->
    <context:property-placeholder location="classpath:db.properties"/>
    <!--    2.连接池-->
    <bean id="pooledDataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
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

<!--  ===============================  配置和mybatis的整合======================-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
<!--        指定mybatis的全局配置文件的位置-->
        <property name="configLocation" value="classpath:mybatis-config.xml"></property>
<!--        指定数据源-->
        <property name="dataSource" ref="pooledDataSource"></property>
<!--        指定mybatis，mapper文件的位置-->
        <property name="mapperLocations" value="classpath:mapper/*.xml"></property>
    </bean>

<!--    配置扫描器，将mybatis接口的实现加入到ioc容器中-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
<!--        扫描所有dao接口的实现，加入到ioc容器中-->
        <property name="basePackage" value="com.lym.mapper"></property>
    </bean>
<!--    =================================================================================-->

<!--    事务控制的配置-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
<!--        控制住数据源-->
        <property name="dataSource" ref="pooledDataSource"/>
    </bean>

<!--    开启基于注解的事务，使用xml配置形式的事务（必要主要的都是使用配置式）-->
    <aop:config>
<!--        切入点表达式-->
<!--        前面的双点是即使service包下还有子级也行  后面的是代表任意个参数-->
        <aop:pointcut id="txPoint" expression="execution(* com.lym.service..*(..))"/>
          <!--配置事务增强-->
        <aop:advisor advice-ref="txAdvice" pointcut-ref="txPoint"></aop:advisor>
    </aop:config>

<!--    配置事务增强，事务如何切入-->
    <tx:advice id="txAdvice">
<!--        tx:attributes这里要看一下导入的头文件，如果头文件错误的，就不会出现这个attributes方法-->
        <tx:attributes>
<!--            所有方法都是事务方法-->
            <tx:method name="*"/>
<!--            以get开始的所有请求-->
            <tx:method name="get*" read-only="true"/>
        </tx:attributes>
    </tx:advice>

<!--    spring配置文件的核心点（数据源，与mybatis的整合，事务控制）-->
</beans>
```

```xml
////////////////mybatis-config.xml////////////
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <settings>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
    </settings>
    <!--    配置数据源，交给spring来做-->
    <typeAliases>
        <package name="com.lym.pojo"/>
    </typeAliases>

</configuration>
```

```properties
/////////////db.properties////////////////
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/ssmframer?useSSL=false&useUnicode=true&characterEncoding=utf8
jdbc.username=root
jdbc.password=1234
```

5. 使用mybatis的逆向工程，生成对应的bean以及mapper

http://mybatis.org/generator/configreference/xmlconfig.html generator使用文档先导入generator的依赖坐标

```xml
<!-- https://mvnrepository.com/artifact/org.mybatis.generator/mybatis-generator-core -->
<dependency>
    <groupId>org.mybatis.generator</groupId>
    <artifactId>mybatis-generator-core</artifactId>
    <version>1.4.0</version>
</dependency>
```

```xml
要先下载其插件，然后导入这个依赖 可以不用写getset方法和  无参全参构造器 加上注解就行了
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.20</version>
    <scope>provided</scope>
</dependency>
```







```xml
//////////////mavengenerator.xml////////////////////
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>

    <context id="DB2Tables" targetRuntime="MyBatis3">
<!--        属性设置不带注释的生成-->
        <commentGenerator>
            <property name="suppressAllComments" value="true"/>
        </commentGenerator>
<!--        配置数据库连接-->
        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://localhost:3306/ssmframer"
                        userId="root"
                        password="1234">
        </jdbcConnection>

        <javaTypeResolver >
            <property name="forceBigDecimals" value="false" />
        </javaTypeResolver>
<!--指定java类生成的位置-->
        <javaModelGenerator targetPackage="com.lym.pojo" targetProject=".\src\main\java">
            <property name="enableSubPackages" value="true" />
            <property name="trimStrings" value="true" />
        </javaModelGenerator>
<!--指定sql映射文件生成的位置-->
        <sqlMapGenerator targetPackage="mapper"  targetProject=".\src\main\resources">
            <property name="enableSubPackages" value="true" />
        </sqlMapGenerator>

<!--        指定dao接口生成的位置-->
        <javaClientGenerator type="XMLMAPPER" targetPackage="com.lym.mapper"  targetProject=".\src\main\java">
            <property name="enableSubPackages" value="true" />
        </javaClientGenerator>

<!--        指定每个表的生成策略-->
        <table tableName="tbl_emp" domainObjectName="Employee"></table>
        <table tableName="tbl_dep" domainObjectName="Department"></table>

    </context>
</generatorConfiguration>
```

```java
////////////////////生成代码的java运行文件////////////////////////
@Test
public void test() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
    List<String> warnings = new ArrayList<String>();
    boolean overwrite = true;
    File configFile = new File("mavengenerator.xml");
    ConfigurationParser cp = new ConfigurationParser(warnings);
    Configuration config = cp.parseConfiguration(configFile);
    DefaultShellCallback callback = new DefaultShellCallback(overwrite);
    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
    myBatisGenerator.generate(null);
}
```

5. 测试mapper

   ```xml
   使用spring项目就可以使用的spring的单元测试，可以自动注入我们需要的组件
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-test</artifactId>
       <version>5.3.10</version>
   </dependency>
   ```



6. 测试能否使用并且编写增删改查测试

```java
//1.导入springtest模块
//2.@ContextConfiguration指定spring配置文件的位置
//3.直接autowired

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class EmpMapperTest {
    @Autowired
    DepartmentMapper departmentMapper;
    /**
     * 测试mapper
     */
    @Test
    public void testCRUD(){
//        //1.创建springioc容器
//        ApplicationContext Context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        //2.从容器中取得mapper
//        DepartmentMapper bean = Context.getBean(DepartmentMapper.class);
//推荐使用spring项目就可以使用的spring的单元测试，可以自动注入我们需要的组件
        //1.插入几个部门
//        System.out.println(departmentMapper);

//        departmentMapper.insertSelective(new Department(null,"开发部"));
        departmentMapper.insertSelective(new Department(null,"测试部"));



    }
```





```xml
在applicationContext.xml中添加这个配置 ，，可以实现批量操作
<!--    配置一个可以执行批量的sqlsession-->
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg name="sqlSessionFactory" ref="sqlSessionFactory"></constructor-arg>
        <constructor-arg name="executorType" value="BATCH"></constructor-arg>
    </bean>
```

```java
在测试类中先通过  autowired引入 是sqlsession  然后执行这个批量操作
//        配置一个可以批量操作的sqlsession 在applicationContext.xml
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for(int i=0;i<1000;i++){
            String uid = UUID.randomUUID().toString().substring(0, 5)+i;
            mapper.insertSelective(new Employee(null,uid,"M",uid+"@qq.com",1));
        }
```



```java
package MapperTest;


import com.lym.mapper.DepartmentMapper;
import com.lym.mapper.EmployeeMapper;
import com.lym.pojo.Department;
import com.lym.pojo.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

//1.导入springtest模块
//2.@ContextConfiguration指定spring配置文件的位置
//3.直接autowired

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class EmpMapperTest {
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;
    /**
     * 测试mapper
     */
    @Test
    public void testCRUD(){
//        //1.创建springioc容器
//        ApplicationContext Context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        //2.从容器中取得mapper
//        DepartmentMapper bean = Context.getBean(DepartmentMapper.class);
//推荐使用spring项目就可以使用的spring的单元测试，可以自动注入我们需要的组件
        //1.插入几个部门
//        System.out.println(departmentMapper);

//        departmentMapper.insertSelective(new Department(null,"开发部"));
//        departmentMapper.insertSelective(new Department(null,"测试部"));

//        //2.生成员工数据
//        employeeMapper.insertSelective(new Employee(null,"张三","M","fdsfdsdf@qq.com",1));
//        employeeMapper.insertSelective(new Employee(null,"李四","M","lisi@qq.com",1));
//        employeeMapper.insertSelective(new Employee(null,"王五","F","wangwuf@qq.com",2));
//        employeeMapper.insertSelective(new Employee(null,"赵六","M","zhaoliuf@qq.com",1));

//        配置一个可以批量操作的sqlsession 在applicationContext.xml
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for(int i=0;i<1000;i++){
            String uid = UUID.randomUUID().toString().substring(0, 5)+i;
            mapper.insertSelective(new Employee(null,uid,"M",uid+"@qq.com",1));
        }


    }
```





引入分页插件

```xml
<!--引入分页插件--> 
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper</artifactId>
            <version>5.3.0</version>
        </dependency>
```

```
///////////////放入mybatis-config中
<plugins>
    <plugin interceptor="com.github.pagehelper.PageInterceptor">
        <!-- config params as the following -->
       <!--            //让分页中的数据合理化  不能小于第一页，不能大于最后一页-->
            <property name="reasonable" value="true"/>
    </plugin>
</plugins>
```

7. 查询

   1. 访问index.jsp页面<jsp:forward page="/emps"></jsp:forward>

   2. index.jsp页面发送出查询员工列表请求

      ```jsp
      <%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
      
      <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
      
      <html>
      <head>
          <title>员工列表展示</title>
          <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">
          <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.js"></script>
          <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
      </head>
      <body>
      
      <%--搭建显示页面--%>
          <div class="container">
      <%--        标题行--%>
              <div class="row">
                  <div class="col-md-12">
                      <h1>SSM_CRUD</h1>
                  </div>
              </div>
      <%--    按钮行--%>
              <div class="row">
                  <div class="col-md-4 col-md-offset-8">
                      <button class="btn btn-sm btn-primary">新增</button>
                      <button class="btn btn-sm btn-danger">删除</button>
                  </div>
              </div>
      <%--    显示表格数据行--%>
              <div class="row">
                  <div class="col-md-12">
                      <table class="table table-hover">
                          <tr>
                              <th>#</th>
                              <th>empName</th>
                              <th>gender</th>
                              <th>email</th>
                              <th>deptName</th>
                              <th>操作</th>
                          </tr>
                          <c:forEach items="${pageInfo.list}" var="emp">
                          <tr>
                              <td>${emp.empId}</td>
                              <td>${emp.empName}</td>
                              <td>${emp.gender=="M"?"男":"女"}</td>
                              <td>${emp.email}</td>
                              <td>${emp.department.deptName}</td>
                              <td>
                                  <button class="btn btn-primary btn-sm">
                                      <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑
                                  </button>
                                  <button class="btn btn-danger btn-sm">
                                      <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
                                  </button>
                              </td>
                          </tr>
                          </c:forEach>
                      </table>
                  </div>
              </div>
      
          <%--    显示分页信息栏--%>
          <div class="row">
      <%--        分页文字信息--%>
              <div class="col-md-6">
                  当前第 ${pageInfo.pageNum} 页,总 ${pageInfo.pages} 页，总 ${pageInfo.total} 条记录
              </div>
      <%--        分页条信息--%>
          <div class="col-md-6">
              <nav aria-label="Page navigation">
                  <ul class="pagination">
                      <li><a href="${pageContext.request.contextPath}/emps?pn=1">首页</a></li>
                      <c:if test="${pageInfo.hasPreviousPage}">
                          <li>
                              <a href="${pageContext.request.contextPath}/emps?pn=${pageInfo.pageNum-1}" aria-label="Previous">
                                  <span aria-hidden="true">&laquo;</span>
                              </a>
                          </li>
                      </c:if>
                      <c:forEach items="${pageInfo.navigatepageNums}" var="page_Num">
                          <c:if test="${page_Num==pageInfo.pageNum}">
                              <li class="active"><a href="#">${page_Num}</a></li>
                          </c:if>
                          <c:if test="${page_Num!=pageInfo.pageNum}">
                              <li><a href="${pageContext.request.contextPath}/emps?pn=${page_Num}">${page_Num}</a></li>
                          </c:if>
                      </c:forEach>
                      <c:if test="${pageInfo.hasNextPage}">
                          <li>
                              <a href="${pageContext.request.contextPath}/emps?pn=${pageInfo.pageNum+1}" aria-label="Next">
                                  <span aria-hidden="true">&raquo;</span>
                              </a>
                          </li>
                      </c:if>
                      <li><a href="${pageContext.request.contextPath}/emps?pn=${pageInfo.pages}">末页</a></li>
                  </ul>
              </nav>
          </div>
          </div>
      
          </div>
      </body>
      </html>
      ```

   3. 

      1. EmployeeController  来接受请求，查出员工数据

         ```java
            controller   注入service
         @GetMapping("/emps")
             public String getEmps(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
                 //引入pagehelper插件
                 //在查询之前只需要调用
                 //传入页码，以及每页的大小
                 PageHelper.startPage(pn,5);
                 //startPage后面紧跟的查询就是一个分页查询
                 List<Employee> emps = employeeService.getEmps();
                 //使用pageinfo包装查询后的结果，只需要将pageInfo交给页面就行了
                 //封装了详细的分页信息，包括有我们查询出来的数据
                 PageInfo page = new PageInfo(emps,5);
                 model.addAttribute("pageInfo",page);
                 return "list";
             }
         }
         
         
         
         
         service  注入mapper
         
         @Override
             public List<Employee> getEmps() {
                 return employeeMapper.selectByExamplewithDept(null);
             }
         ```

   4. 来到list页面进行展示    

   

   8. 改造项目      直接使用c:标签只适用于 浏览器服务器的交互    使用ajax以及json适用于其他平台也可以的交互性能，，改造项目      使用responsbody传json字符串，需要先导入jackson的依赖包

      ```xml
      <dependency>
          <groupId>com.fasterxml.jackson.core</groupId>
          <artifactId>jackson-databind</artifactId>
          <version>2.13.0</version>
      </dependency>
      ```

      ```java
      @RequestMapping("/emps")
      @ResponseBody
      public Msg getEmpsWithJson(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
          //传入页码，以及每页的大小
          PageHelper.startPage(pn,5);
          //startPage后面紧跟的查询就是一个分页查询
          List<Employee> emps = employeeService.getEmps();
          //使用pageinfo包装查询后的结果，只需要将pageInfo交给页面就行了
          //封装了详细的分页信息，包括有我们查询出来的数据
          PageInfo page = new PageInfo(emps,5);
          return Msg.success().add("pageInfo",page);
      }
      ```

      ```java
      //定义json的类
      package com.lym.pojo;
      
      import java.util.HashMap;
      import java.util.Map;
      
      public class Msg {
          //状态码 100 成功  200失败  自定义的
          private int code;
          //用户要返回给浏览器的数据
          private String msg;
          //用户返回给浏览器的数据
          private Map<String,Object> extend = new HashMap<String,Object>();
      
          public static Msg success(){
              Msg result = new Msg();
              result.setCode(100);
              result.setMsg("处理成功！");
              return result;
          }
      
          public static Msg fail(){
              Msg result = new Msg();
              result.setCode(200);
              result.setMsg("处理失败！");
              return result;
          }
      
          //采用链式编程的思维，将数据携带
          public Msg add(String key,Object value){
              this.getExtend().put(key,value);
              return this;
      
          }
      
          public int getCode() {
              return code;
          }
      
          public void setCode(int code) {
              this.code = code;
          }
      
          public String getMsg() {
              return msg;
          }
      
          public void setMsg(String msg) {
              this.msg = msg;
          }
      
          public Map<String, Object> getExtend() {
              return extend;
          }
      
          public void setExtend(Map<String, Object> extend) {
              this.extend = extend;
          }
      
      }
      ```

      1. index.jsp页面直接发送ajax请求，进行员工分页数据的查询

         ```js
         <script type="text/javascript">
             //1.在页面加载完成以后，直接去发送一个ajax请求，要到分页数据
             $(function(){
                 $.ajax({
                     url:"${pageContext.request.contextPath}/emps",
                     data:"pn=1",
                     type:"get",
                     success:function (result){
                         console.log(result);
                     }
                 })
             })
         </script>
         ```

      2. 服务器将查出的数据，以json字符串的形式返回给浏览器

      3. 浏览器收到js字符串，可以使用js对json进行解析，使用js通过dom增删改页面

      

   

   ```jsp
   <%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
   
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
   
   <html>
   <head>
       <title>员工列表展示</title>
       <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">
       <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.js"></script>
       <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
   </head>
   <body>
   
       <%--搭建显示页面--%>
       <div class="container">
           <%--        标题行--%>
           <div class="row">
               <div class="col-md-12">
                   <h1>SSM_CRUD</h1>
               </div>
           </div>
           <%--    按钮行--%>
           <div class="row">
               <div class="col-md-4 col-md-offset-8">
                   <button class="btn btn-sm btn-primary">新增</button>
                   <button class="btn btn-sm btn-danger">删除</button>
               </div>
           </div>
           <%--    显示表格数据行--%>
           <div class="row">
               <div class="col-md-12">
                   <table class="table table-hover" id="emps_table">
                       <thead>
                           <tr>
                               <th>#</th>
                               <th>empName</th>
                               <th>gender</th>
                               <th>email</th>
                               <th>deptName</th>
                               <th>操作</th>
                           </tr>
                       </thead>
                       <tbody>
   
                       </tbody>
   
                   </table>
               </div>
           </div>
   
           <%--    显示分页信息栏--%>
           <div class="row">
               <%--        分页文字信息--%>
               <div class="col-md-6" id="page_info_area">
   
               </div>
               <%--        分页条信息--%>
               <div class="col-md-6" id="page_nav_area">
   
               </div>
           </div>
   
       </div>
       <script type="text/javascript">
           //1.在页面加载完成以后，直接去发送一个ajax请求，要到分页数据
           $(function(){
               to_page(1);
           })
   
           function to_page(pn){
               $.ajax({
                   url:"${pageContext.request.contextPath}/emps",
                   data:"pn="+pn,
                   type:"get",
                   success:function (data){
                       //1.解析显示员工数据
                       builder_emps_table(data);
                       //2.解析并显示分页信息  分页条 分页栏
                       build_page_info(data);
                       builder_page_nav(data);
                   }
               })
           }
   
   
           function builder_emps_table(data){
               //清空  每次请求这个函数时候都做出清空操作，否则会出现点击下一页出来第一页和第二页的数据
               $("#emps_table tbody").empty();
               //将对象赋给emps
               var emps = data.extend.pageInfo.list;
               //使用jquery的each方法遍历
               $.each(emps,function (index,item){
                   var empIdTd = $("<td></td>").append(item.empId);
                   var empNameTd = $("<td></td>").append(item.empName);
                   var genderTd = $("<td></td>").append(item.gender=="M"?"男":"女");
                   var emailTd = $("<td></td>").append(item.email);
                   var deptNameTd = $("<td></td>").append(item.department.deptName);
                   var editBtn = $("<button></button>").addClass("btn btn-sm btn-primary")
                   .append($("<span></span>").addClass("glyphicon glyphicon-pencil"))
                       .append("编辑");
                   var delBtn = $("<button></button>").addClass("btn btn-sm btn-danger")
                       .append($("<span></span>").addClass("glyphicon glyphicon-trash"))
                       .append("删除");
                   var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
                   $("<tr></tr>").append(empIdTd)
                       .append(empNameTd)
                       .append(genderTd)
                       .append(emailTd)
                       .append(deptNameTd)
                       .append(btnTd)
                       .appendTo("#emps_table tbody")
                   ;
               })
           }
           //解析显示分页信息
           function build_page_info(data){
               $("#page_info_area").empty();
               $("#page_info_area").append("当前第"+data.extend.pageInfo
                   .pageNum+"页,总"+data.extend.pageInfo.pages+"页，总"+data.extend.pageInfo.total+"条记录");
           }
           //解析显示分页条
           function builder_page_nav(data){
               $("#page_nav_area").empty();
               //page_nav_area
               var ul = $("<ul></ul>").addClass("pagination");
               var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
               var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
               if(data.extend.pageInfo.hasPreviousPage==false){
                   firstPageLi.addClass("disabled");
                   prePageLi.addClass("disabled");
               }else{
                   //为元素添加点击翻页的事件
                   firstPageLi.click(function (){
                       to_page(1)
                   });
                   prePageLi.click(function(){
                       to_page(data.extend.pageInfo.pageNum-1);
                   });
               }
               var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
               var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
               if(data.extend.pageInfo.hasNextPage==false){
                   lastPageLi.addClass("disabled");
                   nextPageLi.addClass("disabled");
               }else{
                   nextPageLi.click(function(){
                       to_page(data.extend.pageInfo.pageNum+1);
                   });
                   lastPageLi.click(function(){
                       to_page(data.extend.pageInfo.pages);
                   });
               }
               //添加首页和前一页的提示
               ul.append(firstPageLi).append(prePageLi).append(prePageLi);
               //给ul添加页码的提示
               $.each(data.extend.pageInfo.navigatepageNums,function (index,item){
                   var numLi = $("<li></li>").append($("<a></a>").append(item).attr("href","#"));
                   if(data.extend.pageInfo.pageNum==item){
                       numLi.addClass("active");
                   }
                   numLi.click(function (){
                       to_page(item);
                   })
                   ul.append(numLi);
               })
               //添加下一页和末页的提示
               ul.append(nextPageLi).append(lastPageLi);
               //把ul加入到nav
               var navEle = $("<nav></nav>").append(ul);
               navEle.appendTo("#page_nav_area");
           }
       </script>
   </body>
   </html>
   ```

   

      新增功能 -------逻辑

   1. 在index.jsp页面点击新增按钮
   2. 在弹出模态框之前，发出ajax请求，查询出部门信息，显示在下拉列表
   3. 弹出新增对话框
   4. 去数据库查询到部门列表，显示在对话框中
   5. 用户输入数据完成保存
   6.    23  24  25 26  27前端校验跳过了
   
   
   
   
   
   更新，修改
   
   1. 点击编辑，
   
   2. 弹出用户修改的模态框（显示用户信息）
   
   3. 点击更新，完成用户修改
   
   4. ```javascript
      
              $("#emp_add_modal_btn").click(function(){
                  // 在弹出模态框之前，发出ajax请求，查询出部门信息，显示在下拉列表
                  getDepts("#depts_add_select");
                  // 弹出模态框
                  $("#empAddModal").modal({
                      backdrop:"static"
                  });
              });
              // 查询出部门信息，显示在下拉列表
              //为其添加属性,因为添加以及修改功能的下拉都是一样的
              function getDepts(ele){
                  //清空之前下拉列表的值
                  $(ele).empty();
                  $.ajax({
                      url: "${pageContext.request.contextPath}/depts",
                      type: "GET",
                      success: function (data){
                          // console.log(data);
                          // {"code":100,"msg":"处理成功！","extend":{"depts":[{"deptId":1,"deptName":"开发部"},{"deptId":2,"deptName":"测试部"}]}}
                          // 显示部门信息在下拉列表中
                          // $("#depts_add_select").append("");
                          $.each(data.extend.depts,function(index,item){
                              var optionEle = $("<option></option")
                                  .append(item.deptName).attr("value",item.deptId);
                              optionEle.appendTo(ele);
                          });
                      }
                  });
              }
   
   
   ```javascript
          $("#emp_save_button").click(function (){
              //1.模态框中填写的表单数据提交给服务器进行保存
          // 发送ajax请求保存员工
              //序列化表格中的数据
              // alert($("#modal_add_emp").serialize());
              // empName=hj&email=LYM_199%40163.com&gender=F&dId=1
              $.ajax({
                 url: "${pageContext.request.contextPath}/emp",
                 type: "POST",
                  //序列化表格中的数据
                  data: $("#modal_add_emp").serialize(),
                  success:function (data){
                     // alert(data.msg);
                      //    //员工保存成功
                      //     //1.关闭模态框
                          $("#empAddModal").modal('hide');
                      //     //2.到最后页码显示刚才的数据
                      //     //发送ajax请求,显示最后一页的数据即可
                      //     //将总记录数来作为页码,肯定大于其最后一页
                          to_page(totalRecord);
                  }
              });
          });
      
          //1.我们是在按钮创建之前就绑定了click,所以绑定不上
          //(1) 可以在创建按钮的时候绑定 (2) 绑定单击.live[这个方法在新版本的jquery失效了]
          //jquery新版没有live,使用on来进行替代
          $(document).on("click",".edit_btn",function (){
              // alert("edit");
              //0.查出员工信息.显示员工信息
              //1.查出部门信息,显示部门列表
              //
              // 弹出模态框
              $("#empEditModal").modal({
                  backdrop:"static"
              });
              getDepts("#depts_update_select");
              getEmp($(this).attr("edit-id"));
              //把员工的id传递给模态框的更新按钮
              $("#emp_update_button").attr("edit-id",$(this).attr("edit-id"));
          });
      
          function getEmp(id){
              $.ajax({
                  url: "${pageContext.request.contextPath}/emp/"+id,
                  type: "GET",
                  success: function(data){
                      // console.log(data);
                      var empData = data.extend.emp;
                      $("#email_update_input").val(empData.empName);
                      $("#email_update_input").val(empData.email);
                      $("#empEditModal input[name=gender]").val([empData.gender]);
                      $("#empEditModal select").val([empData.dId]);
      
                  }
      
              });
          }
      
          //点击更新,更新员工信息
          $("#emp_update_button").click(function(){
              //发送ajax请求,保存更新员工信息
              $.ajax({
                  url: "${pageContext.request.contextPath}/emp/"+$(this).attr("edit-id"),
                  type: "PUT",
                  data: $("#empEditModal form").serialize(),
                  success:function (data){
                      alert(data.msg);
                  }
              });
      
          });
   ```
   
   

