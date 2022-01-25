# day13 第二部分  servlet

* ## **servlet： **     server  applet[运行在服务器端的小程序]

* 概念：运行在服务器端的小程序

  ​	* servlet就是一个接口，定义了java类被浏览器访问到（tomcat识别）的规则。

  	* 自定义一个类，实现servlet接口，复写方法

* **快速入门：**

  1. 创建javaEE项目
  2. 定义一个类，实现servlet 接口
  3. 重写servlet方法
  4. 配置servlet





**步骤**

1. 创建servlet包，创建Demoservlet类实现servlet方法
2. 在web.xml文件中配置servlet      servlet-mapping

```xml
<!--配置servlet-->
<servlet>
    <servlet-name>demo</servlet-name>    <!--给这个servlet起一个名字-->
    <servlet-class>com.lym.servlet.ServletDemo01</servlet-class>     <!--配置servlet的全类名-->
    <load-on-startup>1</load-on-startup>       值为负数，第一次调用时候servlet创建，值为0或正数，服务器启动时候创建
</servlet>
<!--映射servlet-->
<servlet-mapping>
    <servlet-name>demo</servlet-name>
    <url-pattern>/hello</url-pattern>   <!--访问这个路径，即映射到了这个servlet-->
</servlet-mapping>
```

**servlet执行原理**

1. 当服务器接收到客户端浏览器的请求后，会解析请求URL路径，获取访问的Servlet的资源路径
2. 查找web.xml文件，是否有对应的<url-pattern>标签体内容
3. 如果有，则找到对应的<servlet-class>全类名
4. tomcat会将字节码文件加载进内存，并且创建其对象
5. 调用其方法

* **servlet的方法**



servlet的init方法，只执行一次，说明一个servlet在内存中只存在一个对象，servlet是单例的

* 多个用户同时访问，可能存在线程安全问题
* **解决：**尽量不要在servlet中定义成员变量。即使定义了成员变量，也不要对其修改值



servlet的service方法，执行多次

每次访问servlet时，service方法都会调用一次





servlet3.0以上，可以不需要web.xml了，  支持注解配置

**步骤：**

1. 创建javaEE项目，选择servlet3.0以上的版本，可以不创建web.xml
2. 定义一个类，实现servlet接口
3. 复写方法
4. 在类上使用@Webservlet来配置     @Webservlet（“资源路径”）



**WEB-INF目录下的资源不能被浏览器直接访问**





http:超文本传输协议

* 传输协议：定义了，客户端和服务器端通信时，发送数据的格式
* **特点**
  1. 基于TCP/IP的高级协议
  2. 默认端口号：80
  3. 基于请求/响应模型的   ：  一次请求对应一次响应
  4. 无状态的：每次请求之间相互独立，不能交互数据



**request功能：**

1. 获取请求行数据
   * GET     /day14/demo01?name=zhangssna   HTTP/1.1
     * **方法：**
       * 1.获取请求方式  ：  GET
         * String  getMethod()
       * 2.**获取虚拟目录 ：/day14**
         * String  getContextPath()
       * 3.获取servlet路径  /demo01
         * String  getServletPath()
       * 4.获取get方式请求参数：name=zhangsan
         * String getQueryString()
       * **5.获取请求URI**
         * String getRequestURI()   :/day14/demo01
         * StringBuffer  getRequestURL()         :http://localhost/day14/demo01
       * 6.获取协议及版本   ：  HTTP/1.1
         * String  getProtocol()
       * 7.获取客户机的IP地址
         * String  getRemoteAddr()
2. 获取请求头数据
   * String getHeader(String name):通过请求头的名称获取请求头的值
3. 获取请求体数据







**request的其他功能**

1. 获取请求参数的通用方式       不论是get还是post方法都可以进行你使用

   1. String getParameter(String  name):根据参数名称获取参数值  eg:username=aaa  name传入ysername,获取到aaa
   2. String[]  getParameterValues(String name):根据参数名称获取参数值的数组  例如：多选按钮
   3. Enumeration<String>    getParameterNames():  获取所有请求的参数名称
   4. Map<String,String[]>  getParameterMap(): 获取所有参数的map集合
      * **解决request请求的中文乱码问题**
        * get方式：tomcat方法，已经将get方式乱码问题解决了
        * post方式：会乱码，，，，解决方案：在获取参数前，设置request的编码，
        * **request.setCharacterEncoding("utf-8")**

    

2. **请求、转发**【一种在服务器内部的资源跳转方式】

   1. 步骤
      1. 通过request对象获取请求转发器对象：  RequestDispatcher  **getRequestDispatcher(String  path)**
      2. 使用RequestDispatcher对象进行转发
      3. **一般使用链式编程**  **即： ** request.getRequestDispatcher(String  path).forward(request,response);  获取转发器对象，完成转发
   2. **转发的特点**
      1. 浏览器地址栏路径不发生变化
      2. 只能转发到当前服务器内部的资源中
      3. 转发是一次请求  

3. **共享数据**

   **域对象**：一个有作用范围的对象，可以在范围内共享数据

   request域：代表一次请求的范围   【一般用于请求转发的多个资源中共享数据】

   **方法：**

   	* setAttribute(String  name,Object  obj)     :     存储数据
   	* Object    getAttribute(String  name)   通过键获取值 
   	* void   removeAttribute(String  name)    通过键移除键值对

4. 获取ServlerContext

   1.  ServlerContext  getServlerContext()



 **登录案例**

开发步骤：

1. 创建项目，导入html页面，配置文件，jar包

2. 创建数据库环境

3. 创建domain包，创建User实体类

4. 创建dao包，写UserDao 【操作数据 库中user表的增删改查的类】在这之前先创建工具类JDBCUtils获取到数据库连接

5. 创建servlet

   

