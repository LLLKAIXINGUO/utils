# day28 第一部分 springmvc 遇见狂神说

javaSe： 认真学习

javaWeb:认真学习

框架： 研究官方文档，锻炼自学能力，锻炼笔记能力，锻炼项目能力

springmvc   vue     springboot       springcloud         linux



##### SpringMVC的执行流程

SpringMVC:SSM框架整合

MVC ： 模型（dao、service） 视图（jsp） 控制器（servlet）

前端      数据传输       实体类

dto：数据传输的时候的对象           也相当于实体类

```xml
导入依赖
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.3.10</version>
</dependency>
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>servlet-api</artifactId>
    <version>2.5</version>
</dependency>
<dependency>
    <groupId>javax.servlet.jsp</groupId>
    <artifactId>jsp-api</artifactId>
    <version>2.1</version>
</dependency>
<dependency>
     <groupId>javax.servlet.jsp.jstl</groupId>
     <artifactId>jstl</artifactId>
     <version>1.2</version>
</dependency>
```

http://localhost:8080/springmvc_01servlet_war_exploded/hello?method=delete

?method=add

```html
<form action="/hello" method="post">
    <input type="text" name="method">
    <input type="submit" value="登录">
</form>
```

```java
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取前端参数
        String method = req.getParameter("method");
        if(method.equals("add")){
            req.getSession().setAttribute("msg","执行了add方法");
        }
        if(method.equals("delete")){
            req.getSession().setAttribute("msg","执行了delete方法");
        }
        //2.调用业务层
        //3.视图转发或者重定向
        req.getRequestDispatcher("/WEB-INF/jsp/testindex.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
```

```xml
web.xml
<servlet>
    <servlet-name>hello</servlet-name>
    <servlet-class>com.lym.servlet.HelloServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>hello</servlet-name>
    <url-pattern>/hello</url-pattern>
</servlet-mapping>
```





MVVM     M    V        VM ViewModel：双向绑定

springmvc的特点

1. 轻量级，简单易学
2. 高效，基于请求相应的MVC框架
3. 与Spring兼容性好，无缝结合
4. 约定优于配置
5. 功能强大：RESTful、数据验证、格式化、本地化、主题等
6. 简介灵活

##### Spring的web框架围绕DispatcherServlet  【调度Servlet】设计



中心控制器：  DispatcherServlet 

DispatcherServletd的作用是将请求分发到不同的处理器

spring2.5 开始使用注解的controller来配置



1. 新建一个springmvc       的moudle

2. add web.application支持

3. 配置web.xml，注册dispatcherServlet

   ```xml
   <!--1.注册DispatcherServlet-->
   <servlet>
       <servlet-name>springmvc</servlet-name>
       <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
       <!--关联一个springmvc的配置文件:【servlet-name】-servlet.xml-->
       <init-param>
           <param-name>contextConfigLocation</param-name>
           <param-value>classpath:springmvc-servlet.xml</param-value>
       </init-param>
       <!--启动级别-1-->
       <load-on-startup>1</load-on-startup>
   </servlet>
   <!--/ 匹配所有的请求；（不包括.jsp）-->
   <!--/* 匹配所有的请求；（包括.jsp）-->
   <servlet-mapping>
       <servlet-name>springmvc</servlet-name>
       <url-pattern>/</url-pattern>
   </servlet-mapping>
   ```

4. 创建springmvc-servlet.xml  并配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd">
    
</beans>
```

5. 添加处理器映射器

   ```xml
   <bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>
   ```

6. 添加处理器适配器

   ```xml
   <bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"/>
   ```

   

7. 添加视图解析器

```xml
<!--视图解析器:DispatcherServlet给他的ModelAndView-->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="InternalResourceViewResolver">
    <!--前缀-->
    <property name="prefix" value="/WEB-INF/jsp/"/>
    <!--后缀-->
    <property name="suffix" value=".jsp"/>
</bean>
```

8. 创建一个 com.lym.controller      ----------> HelloController

```java
public class HelloController implements Controller {
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        //ModelAndView 模型和视图
        ModelAndView mv = new ModelAndView();
        //封装对象，放在ModelAndView中。Model
        mv.addObject("msg", "HelloSpringMVC!");
        //封装要跳转的视图，放在ModelAndView中
        mv.setViewName("hello");             //拼接前缀后缀 /WEB-INF/jsp/hello.jsp
        //: /WEB-INF/jsp/hello.jsp
        return mv;
    }
}
```

9. 将自己的类交给SpringIOC容器，注册bean

```xml
<bean id="/hello" class="com.lym.controller.HelloController"/>
```

10. 启动tomcat

11. **运行404错误，查看控制台，看看是不是少了什么jar包**
12. **如果jar包存在，显示无法输出，就在IDEA的项目发布中，添加lib依赖**
13. **重启tomcat即可解决**

在project structure  的Artifaces 点击项目 在web-inf下创建  lib文件夹目录，把所有的包都给导进去           然后apply一下           重启tomcat

## 执行流程

1. DispatcherServlet表示前端控制器，是整个SpringMVC的控制中心，用户发送请求，DispatcherServlet接收请求并拦截请求
   * 假设请求的url是：   http://localhost:8080/SprongMVC/hello
   * http://localhost:8080服务器域名
   * SpringMVC部署在服务器上的web站点
   * hello表示控制器
   * 通过分析，如上url表示为：请求位于服务器localhost:8080上的SpringMVC站点的hello控制器
2. HandlerMapping为处理器映射器，DispatcherServlet调用HandlerMapping，HandlerMapping根据请求url查找Handler
3. HandlerExecution表示具体的Handler，其主要作用是根据url查找控制器，如上url被查找控制器为：hello
4. HandlerExecution将解析后的信息传递给DispatcherServlet，如解析控制器映射等
5. HandlerAdapter表示处理器适配器，其按照为特定的规则去执行Handler
6. Handler让具体的Controller执行。
7. Controller将具体的执行信息返回给HandlerAdapter, 如ModelAndView
8. HandlerAdapter将视图逻辑名或模型传递给DispatcherServlet
9. DispatcherServlet调用视图解析器（ViewResolver）来解析HandlerAdapter传递的逻辑视图名
10. 视图解析器将解析的逻辑视图名传给DispatcherServlet
11. DispatcherServlet根据视图解析器解析的视图结果，调用具体的视图
12. 最终视图呈现给用户



在springmvc中      /                /*的区别

/:只匹配所有的请求，不会去匹配jsp页面

/*: 匹配所有的请求，会去匹配jsp页面





使用注解开发springmvc

1. 新建一个moudle
2. 导包问题   pom.xml builder上加入  xml  properties 的过滤文件

3. ```xml
   <servlet>
       <servlet-name>springmvc</servlet-name>
       <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
       <init-param>
           <param-name>contextConfigLocation</param-name>
           <param-value>classpath:springmvc-servlet.xml</param-value>
       </init-param>
       <!--启动级别-1-->
       <load-on-startup>1</load-on-startup>
   </servlet>
   <servlet-mapping>
       <servlet-name>springmvc</servlet-name>
       <url-pattern>/</url-pattern>
   </servlet-mapping>
   ```

4. 在resources下创建 springmvc-servlet.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd">  
    
</beans>
```

添加内容后为：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context.xsd
    http://www.springframework.org/schema/mvc
    http://www.springframework.org/schema/mvc/spring-mvc.xsd">

<!--    自动扫描包,让指定包下的注解生效,由IOC容器统一管理-->
    <context:component-scan base-package="com.lym.controller"/>
    <mvc:default-servlet-handler/>

<!--    支持mvc注解驱动
        在spring中一般采用@RequestMapping注解来完成映射关系
        要i想使@RequestMapping注解生效,必须
        向上下文中注册DefaultAnnotationHandlerMapping
        这两个实例分别是在类级别和方法级别处理
        而annotation-driven配置帮助我们自动完成上述两个实例的注入-->
    <mvc:annotation-driven/>

<!--    视图解析器-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
        <property name="prefix" value="WEB-INF/jsp/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

</beans>
```

```java
@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String hello(Model model){
        //封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("msg","dsfffffffffff");
        map.put("msger","信息输出");
        map.put("error","输出错误");
        model.addAllAttributes(map);
        return "hello";   // 会被视图解析器处理
    }
}
```

```jsp
 ${msg}
${error}
```



如果使用的是@RestController注解     这个类就不会被视图解析器解析，返回的就直接是字符串，    以后json数据会用这个





注意点： 使用springmvc必须配置的三大件

处理器映射器、处理器适配器、视图解析器

在注解中前两个不用配置        < <mvc:annotation-driven/> >一句话搞定



#### controller配置总结

创建一个项目，添加 web.application结构

配置web.xml





### **RestFul风格**:可以通过不同的请求方式来实现不同的效果，如：请求地址一样，但是功能不一样

****

**RestFul就是一个资源定位及资源操作的风格。不是标准也不是协议，只是一种风格，基于这个风格设计的软件可以更间接，更有层次，更易于实现缓存等机制**



1. 再新建一个类RestFulController 
2. 在Spring MVC中可以使用@PathVariable注解，让方法参数的值对应绑定到一个URI模板变量上。



```java
@Controller
public class RestFulController {
    @RequestMapping("r1/test01")
    public String add(int a, int b, Model model) {
        int result = a+b;
        model.addAttribute("msg", "结果:" + result);
        return "restful";
    }
}
```

http://localhost:8080/springmvc_04_controlleraa_war_exploded/r1/test01 报错500

http://localhost:8080/springmvc_04_controlleraa_war_exploded/r1/test01?a=1&b=2输出了结果       拼接后





http://localhost:8080/springmvc_04_controlleraa_war_exploded/r1/test01 404错误

http://localhost:8080/springmvc_04_controlleraa_war_exploded/r1/test01/1/10 输出结果

```java
@Controller
public class RestFulController {
    @RequestMapping("r1/test01/{a}/{b}")
    public String add(@PathVariable int a, @PathVariable int b, Model model) {
        int result = a+b;
        model.addAttribute("msg", "结果:" + result);
        return "restful";
    }
}
```

**转发和重定向**



**乱码问题**

```
解决思维：
用过滤器解决乱码

创建一个过滤器

实现Filter接口

在过滤器方法中  

request.setCharacterEncoding("utf-8");

response.setCharacterEncoding("utf-8");

 chain.doFilter(requet,response); //放行



然后一定要在web.xml中注册一下              get方式不乱码了，但是post方式依旧有乱码
```



```xml
springmvc帮我们解决的
配置springmvc的乱码过滤   web.xml中
<filter>
    <filter-name>encoding</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>utf-8</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>encoding</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```





# json   

前后端分离时代：  

后端部署后端：提供接口

前端独立部署：负责渲染后端的数据

约定：使用json数据交换格式



**json:是一种轻量级的数据交换格式，目前使用特别广泛**

* 采用完全独立于编程语言的文本格式来存储和表示数据
* 简介和清晰的层次结构使得json成为理想的数据交换格式
* 易于人阅读和编写，同时也易于机器解析和生成，并有效的提升网络传输效率

JSON和  Javascript互相转换

```javascript
<script type="text/javascript">
    var user={
        name:"张三",
        age:3,
        sex:"女"
    };
    //将js对象转换为json对象
    var jsonstring = JSON.stringify(user);
    //将json对象转换为js对象
    var obj = JSON.parse(jsonstring);
    console.log(jsonstring);
    console.log(user);
    console.log(obj);

</script>
```

