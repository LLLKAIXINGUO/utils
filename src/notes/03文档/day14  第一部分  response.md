# day14  第一部分  response

http协议

1. 请求消息：客户端发送给服务器端的数据
   * 数据格式：
     1. 请求行
     2. 请求头
     3. 请求空行
     4. 请求体
2. 响应消息：服务器端发给送给客户端的数据
   * 数据格式：
     1. 响应行
        1. 组成：协议/版本  响应状态码  状态码描述
        2. 响应状态码，： 服务器告诉客户端浏览器本次请求和响应的一个状态
           1. 状态码都是3位数字
           2. 分类：
              1.   1XX   : 服务器接收客户端的消息，但没有接收完成，等待一段时间，发送的1XX多状态码
              2.   2xx   ： 成功 ，  代表的有  200
              3.  3XX     重定向： 302（重定向）  304（访问缓存）
              4.  4XX     客户端错误：  代表404  （请求路径没有对应的资源）   405请求方式没有对应的doXxx方法
              5.  5XX     服务器端错误   代表：500（服务器的内部出现异常）
     2. 响应头
        1. 格式：  头名称：值
        2. 常见的响应头：
           1. Content-Type:  服务器告诉客户端本次响应体数据格式以及编码格式【将来要通过这个来解决中文乱码的问题】
           2. Content-disposition:服务器告诉客户端以什么格式打开响应体数据
              * 值有：  in-line:默认值，在当前也买你内打开
              * attachment: 以附件形式打开响应体。  eg：文件下载
     3. 响应空行
     4. 响应体



重定向方法：

response.setStatus(302);

response.setHeader("location","/day【虚拟目录】/servlet路径")



简单的重定向方法 【因为  设置状态信息  和"location",都是固定的】

response.sendRedirect("/day【虚拟目录】/servlet路径")



**重定向的特点：**

1. 地址栏发生变化
2. 重定向可以访问其他服务器下的资源
3. 重定向是两次请求   不能使用request共享数据       转发可以使用request共享数据





### **路径的写法**

路径分类

* 相对路径:通过相对路径不可以确定唯一资源 ：       ./index.html   
  * 不以/开头，以.开头
  * **规则：**找到当前资源和目标资源之间的相对位置关系
* 绝对路径：通过绝对路径可以确定唯一资源  以“/”开头的路径
  * 如：http://localhost/day15/responseDemo2          /day15/responseDemo2 
  * **规则：**判断定义的路径是给谁用的：判断请求将来从哪儿发出
    * 给客户端浏览器使用，需要加虚拟目录（项目的访问路径）
      * 建议使用**动态获取虚拟目录**  **request.getContextPath()**  
    * 给服务器端使用，不需要加虚拟目录



**服务器端输出到浏览器**

在获取流对象之前，设置流的默认编码设置为  字符集    eg:utf-8

* //response.setCharacterEncoding("字符集")；

告诉浏览器，服务器发送的消息体数据的编码，建议浏览器使用该编码解码

* **response.setHeader("content-type","text/html;charset=utf-8");**  

#### **简单形式设置编码 **   response.setContentType("text/html;charset=utf-8");

### **仅仅使用这一句即可**

1. 获取字符输出流  PrintWrite  pw   =     response.getWrite();

2. 输出数据              pw.write("dfsafdsaf");

   * * ### **解决中文乱码问题**  【乱码原因，编解码使用的字符集不一致】 在获取流数据之前

     * # **response.setContentType("text/html;charset=utf-8");**



验证码的本质是一个图片  字节流





ServletContext对象

1. 概念：代表整个web应用，可以和程序的容器（服务器）来通信

2. 功能

   1. 获取MIME类型：   MIME类型：在互联网通信过程中定义的一种文件数据类型  格式：  大类型/小类型           text/html          image/jpg

      * 获取：  String   getMIMEType(String file)

   2. 域对象：共享数据

      1. setAttribute(String name,Objeect  value)
      2. getAttribute(String name)
      3. removeAttribute(String name)
      4. **ServletContext对象范围：所有用户的数据**

   3. 获取文1件的真实路径（服务器路径）

      * String  getRealPath("")

      

**点击超链接，弹出下载提示框      同时也要解决中文资源名称的乱码问题**

​     黑马 p723  文件下载案例