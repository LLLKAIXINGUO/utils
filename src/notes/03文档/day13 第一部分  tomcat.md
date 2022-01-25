# day13 第一部分  tomcat

#### web服务器软件：

* 服务器：安装了服务器软件的计算机
* 服务器软件：接收用户的请求，处理请求，做出响应
* web服务器软件：接收用户的请求，处理请求，做出响应
  * web服务器软件中，可以部署web项目，让用户通过浏览器来访问这些项目
  * web容器
* 常见的java相关的web服务器软件
  * weblogic:  oracle公司，大型的JAVAEE服务器，支持所有的JAVAEE规范，收费的
  * webSphere:IBM公司的，大型的JAVAEE服务器，支持所有的JAVAEE规范，收费的
  * JBOSS:jboss公司大型的JAVAEE服务器，支持所有的JAVAEE规范，收费的
  * Tomcat：  Apacche基金组织。中小型的JAVAEE服务器，支持少量的JAVAEE规范servlet/jsp,开源的，免费的



**JAVAEE**：java语言在企业级开发中使用的技术规范的总和，一共规定了13项大的规范





**配置**

**部署项目的方式**

1. {直接将项目放到webapps目录下即可}

   * /hello:项目的访问路径-------》虚拟目录
   * 简化部署：“将项目打成一个war包，再将war包放置到webappps目录下，war包会自动解压缩

2. 配置conf/server.xml文件

   * 在<Host>标签体中配置
   * <context docBase="D:\hello" path="/he">
   * docBase:项目存放的路径，  path：虚拟目录

3. 在conf\Catalina\localhost创建任意名称的xml文件。在文件中编写

   <Context docBase="D:\hello" />  

   虚拟目录：xml文件的名称



* **静态项目和动态项目**
  * **目录结构**
    * Java动态项目的目录结构
      * 项目名称
        * --WEB-INF
          * web.xml：该项目的核心配置我呢见
          * classes目录，放置字节码文件
          * lib目录：放置项目依赖的jar包

