# day15 第一部分  cookie     and    session

1. 会话技术
   1. Cookie
   2. Session
2. JSP

#### **会话：**一次会话中包含多次请求和响应 【一次会话：浏览器第一次给服务器资源发送请求，会话建立，直到有一方断开为止】

* # 功能：共享数据【在一次会话的范围内的多次请求间】

方式： 

1. 客户端会话技术：cookie
2. 服务器端会话技术：session

  

**快速入门**

**使用步骤**

1. 创建cookie对象，绑定数据
   * new  Cookie(String  name,String  value)
2. 发送cookie对象
   * response.addCookie(Cookie cookie)
3. 获取cookie，拿到数据
   * Cookie[]   request.getCookies()



可以创建多个cookie对象，使用response调用多次addCookie方法发送cookie即可

cookie在浏览器中保存的时间

1. 默认情况下，当浏览器关闭后，cookie数据被销毁
2. 设置cookie的 持久化存储
   * setMaxAge(int seconds)
     1. 正数：将cookie数据写到硬盘的文件中，持久化存储  。  cookie的存活时间
     2. 负数：默认值
     3. 0：删除cookie信息

**tomcat80.之后，cookie支持中文数据**



cookie的获取范围有多大？？？ 假设在一个tomcat 服务器下，部署了多个web项目，那么这些web项目中cookie能不能共享？

默认情况下cookie不能共享

setPath(String  path):设置cookie的取值范围，默认情况下，设置当前的虚拟目录

如果要共享，则可以使用 setPath(“/”):  当前服务器下部署的所有项目都可以共享

不同服务器间cookie共享问题

* setDomain(String path)  如果设置一级域名相同，那么多个服务器之间可以共享



cookie的特点：

1. cookie存储数据到客户端浏览器
2. 浏览器对于单个cookie的大小有限制,对同一个域名下的cookie的数量也有限制的



作用：

1. cookie一般用于存储少量的不太敏感的数据
2. 在不登陆的情况下，完成服务器对客户端的身份识别  【例如：没有进行百度的登录，对百度的页面进行修改，关闭后下次再打开，设置还是那样子的】



**案例：记住上一次的登录时间**

需求：访问一个servlet，如果是第一次访问，则提示：   您好，欢迎您首次访问

​         如果不是第一次访问，就提示：  欢迎回来，您上次登录的时间为：   显示时间字符串



jsp:  既可以写html 静态代码， 又可以写java代码

jsp本质上就是一个servlet



**jsp的脚本：**  jsp定义java代码的方式

1.   <%  代码%> ：定义的java代码，在service方法中。service方法中可以定义什么，该脚本中就可以定义什么
2. <%！  代码%>：  定义的java代码在jsp转换后的java类的成员位置。【使用比较少】
3. <%=  代码%> ：  定义的java代码会输出到页面中，输出语句会输出什么，它就可以输出什么

**jsp内置对象**：在jsp页面不需要获取和创建，可以直接使用的对象

jsp一共有九个内置对象

* request
* response
* out :   字符输出流。可以将数据输出到页面上，和 response.getWrite()类似  在tomcat





session服务器端的会话技术，在一次会话的多次请求间共享数据，将数据保存在服务器端的对象中。

快速入门：

1. 获取HttpSession对象：

   HttpSession  session  =  request.getSession();

2. 使用HttpSession对象

   Object  getAttribute(String name)

   void setAttribute(String name,Object value)

   void removeAttribute(String name)

3. 原理

   session的实现是依赖cookie的

4. 细节

   1. 当客户端关闭后，服务器不关闭，两次获取的session是否为同一个么
      * 默认情况下，不是同一个 
      * 如果想要期待客户端关闭后，session也能相同，创建一个cookie对象CooKie a=new Cookie("JSESSIONID",session.getId()) ;   a.setMaxAge(60*60);    resp.addCookie(a);
   2. 客户端不关闭，服务器关闭后，两次获取的session是同一个么
      * 不是同一个，服务器关闭了以后，session被销毁了      但是要**确保数据不丢失** 
      * session的钝化：在服务器正常关闭之前，将session对象序列化到硬盘上
      * session的活化：在服务器启动后，将session文件转化为内存中的session对象即可
      * 这两件事，tomcat已经帮我们做了
   3. session的失效时间？
      1. 服务器关闭
      2. session对象调用invalidate()   方法
      3. session默认失效时间是 三十分钟     可以选择性的配置修改



session特点： 

1. session  可以用于存储一次会话的多次请求的数据，存在服务器端
2. session可以存储任意类型，任意大小的数据



session与cookie的区别

1. session存储数据在服务器端，cookie存储在客户端
2. session没有数据大小限制，cookie有
3. session数据安全【存储在服务器】cookie相对于session不安全



