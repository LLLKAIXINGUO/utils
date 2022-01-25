#  day15 第二部分  jsp

1. **jsp**

   1. 指令
      * 作用：用于配置jsp页面，导入资源文件
      * 格式：   <%@指令名称  属性键值对【属性名1=属性值1  属性名2=属性值2.....】 %>
      * 分类：
        * page     配置jsp页面的
          * contentType:等同于response.setContentType()
            1. 设置响应体的MIME类型及字符集
            2. 设置当前jsp页面的编码【只能是高级的IDE才能生效】
        * include  页面包含的，导入页面资源文件
        * taglib   导入资源   标签库
   2. 注释
      1. html注释     <!--    -->      只能注释html代码片段
      2. jsp特有注释  <%--    --%>  推荐使用，可以注释所有
   3. 内置对象  ：在jsp页面中不需要创建，直接使用的对象
       * 变量名                                     真实类型                                      作用
       * pageContext                    PageContext                  域对象，在当前页面共享对象 ，PageContext     可以获取其他八个对象             
       * request                             HttpServletRequest      域对象，一次请求访问的多个资源【转发】
       * session                              HttpSession                    域对象，一次会话的多个请求间
       * application                       SerrvletContext              域对象，所有用户间共享数据
       * response                            HttpServletResponse      响应对象
       * page                                   Object                               当前页面（servlet）的对象 this
       * out                                       JspWrite                             输出对象，数据输出到页面上
       * config                                  ServletConfig          servlet的配置对象
       * exception                          Throwable                 异常对象

2. ##### **MVC开发模式**

   * * **重点**
     * M[model]   V [view]         C[controller]
     * 控制器      Servlet
       1. 获取客户端的输入
       2. 调用模型
       3. 将数据交给视图展示
     * 模型 【业务逻辑操作】 JavaBean  
       * 完成具体的业务操作，如查询数据库，封装对象
     * 视图     展示

   MVC的优缺点

   * 优点：  耦合度低，方便维护，分工协作方便 重用性高
   * 缺点： 使得项目架构变得复杂，对开发人员要求高

3.  **EL表达式**

   1. 概念：  Expression   Language  :表达式语言

   2. 作用：替换和简化jsp页面中java代码的编写

   3. 语法：  ${表达式}             \ ${表达式} 忽略这个el表达式 相当于转译

   4. 使用方式：

      1. 运算

         * 运算符
           1. 算数运算符：   +   -   *   /(div)        %(mod)    
           2. 比较运算符       >       <  ==     !=
           3. 逻辑运算符       &&（and）       ||(or)       ！(not)
           4. **空运算符**        empty
              * 功能：用于判断字符串、集合、数组对象是否为null长度是否为0
              * $[empty list]判断字符串集合数组对象是否为null或者长度为0
              * $[not empty list]表示判断字符串集合数组对象是否不为null,并且长度>0

      2. 获取值

         1. el表达式只能从域对象中获取值

         2. 语法  

            1. ${域名称.键名}：从指定域中获取指定键的值

               * 域名称

                 1. pageScope ----------->pageContext
                 2. requestScope---------->request
                 3. sessionScope----------->session
                 4. applicationScope-------->application(servletContext)

                 举例：在request域中存储了 name=张三

                 获取：  ${requestScope.name}

            2. ${键名}      ：表示依次从最小的域中查找是否有该键对应的值

         3. 获取对象、list集合、map集合的值

            1. 对象：      ${域名称.键名.属性名}     属性名  get/set方法后的名字，首字母变小写
               * 本质上会去调用对象的get方法
            2. list集合         ${域名称.键名[索引]}
               1. eg:    ${list[0]}
            3. map集合      ${域名称.键名.key名称}         ${域名称.键名["key名称"]}

      3. 隐式对象

         * pageContext:

           1. 获取jsp其他八个对象

           **动态获取虚拟目录**      **${pageContext.request.contextPath}**

4. **JSTL标签**

   * JavaServer  Page   Taf  Library   JSP 标准标签库
   * 作用：用于简化和替换jsp页面上的java代码
   * 使用步骤：
     1. 导入jstl相关jar包
     2. 引入标签库 ， taglib指令 ：        <%@ taglib%>
     3. 使用标签
     4. 常用的jstl标签
        1. if                    :相当于Java代码的  if语句
           1. c:if标签，test为必须属性，接收boolean表达式，如果表达式为true则显示标签体的内容，如果表达式为false,不显示标签体的内容
        2. choose         :相当于Java代码的  switch语句
        3. foreach        :相当于Java代码的  for语句

5. **三层架构**





 **登录案例**

开发步骤：

1. 创建项目，导入html页面，配置文件，jar包

2. 创建数据库环境

3. 创建domain包，创建User实体类

4. 创建dao包，写UserDao 【操作数据 库中user表的增删改查的类】在这之前先创建工具类JDBCUtils获取到数据库连接 ，  提供login方法

5. 创建servlet模块  LoginSerclet类

6. 将login.html写入路径     虚拟目录+servlet路径

   1. 在servlet中  

   2. ```java
      //loginServlet中代码
      //1.设置编码
      req.setCharacterEncoding("utf-8");
      //2.获取请求参数
      String username = req.getParameter("username");
      String password = req.getParameter("password");
      //3.封装user对象
      User loginuser = new User();
      loginuser.setUsername(username);
      loginuser.setPassword(password);
      //4.调用UserDao的login方法
      UserDao dao = new UserDao();
      User user = dao.login(loginuser);
      if(user==null){
          //登录失败
          req.getRequestDispatcher("/failServlet").forward(req,resp);
      }else{
          //登录成功
          //先存储数据，然后进行转发
          req.setAttribute("user",user);
          req.getRequestDispatcher("/successServlet").forward(req,resp);
      }
      ```

```java
//faileServlet代码
resp.setContentType("text/html;charset=utf-8");
resp.getWriter().write("登录失败，用户名或密码错误");
```

```java
//successServlet代码
User user = (User) req.getAttribute("user");
if (user!=null) {
    resp.setContentType("text/html;charset=utf-8");
    resp.getWriter().write("登录成功!  "+user.getUsername()+"欢迎您");
}
```

7.创建BeanUtils工具类，简化数据的封装