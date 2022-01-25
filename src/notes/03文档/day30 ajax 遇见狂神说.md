# day30 ajax 遇见狂神说

ajax技术



对于springmvc项目回顾

1. 添加web依赖架构
2. 在web.xml文件中配置  dospatcherservlet【绑定spring配置文件】  以及filter

```xml
web.xml
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

3. 配置spring的配置文件   【扫描注解驱动，静态资源过滤，视图解析器】

```xml
applicationContext.xml
<context:component-scan base-package="com.lym.controller"/>
<context:annotation-config/> //配置json是在这里
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/WEB-INF/jsp"/>
    <property name="suffix" value=".jsp"/>
</bean>
```

4. 先写个controller进行一下测试

```java
@RestController
@RequestMapping("/ajax")
public class AjaxController {
    @RequestMapping("/test01")
    public String test01(){
        return "springmvc配置完成";
    }
}
```







```html
小窗格实现功能
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ifram测试体验页面无刷新</title>
    <script>
        function fun(){
            //所有的值变量，提前获取
            var url = document.getElementById("url").value;
            document.getElementById("ifram1").src=url;
        }
    </script>
</head>
<body>
<div>
    <p>请输入地址：</p>
    <input type="text" id="url">
    <input type="button" value="提交" onclick="fun()">
</div>
    <div>
        <iframe id="ifram1" style="width: 100%;height: 500px"></iframe>
    </div>

</body>
</html>
```



拦截器配置     拦截器是AOP思想的具体应用



拦截器只会拦截访问的控制器（controller）方法   不会加载静态资源的



创建一个  实现HandlerIntercepor接口  去实现它的方法， 在spring的配置文件中注册进去

mvc做的，  aop思想



在web-inf下的所有页面或者资源，只能通过controller或者servlet来访问