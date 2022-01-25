# day18   第一部分   filter   listener

### 过滤器和监听器

**web中的过滤器**：当访问服务器的资源时，过滤器可以将请求拦截下来

过滤器的作用：

* 一般用于完成通用的操作，如登录验证、设置统一编码处理、敏感字符过滤。。。

快速入门：

步骤：

1. 定义一个类，实现接口Filter  (Javax.servlet)

2. 复写方法

   * 在类上加入@WebFilter("/*")     表示对所有资源执行过滤
   * 在doFilter  方法中加入chain.doFilter(request,response);表示放行

3. 配置拦截路径

   @WebFilter("/*")





**过滤器细节**

1. web.xml配置

   1. ```xml
      <filter>
        <filter-name>demo</filter-name>
        <filter-class>com.lym.filter.Demo01Filter</filter-class>
      </filter>
      <filter-mapping>
        <filter-name>demo</filter-name>
        <url-pattern>/demo.jsp</url-pattern> //过滤路径
      </filter-mapping>
      ```

2. 过滤器执行流程

   1. 执行过滤器
   2. 执行放行后的资源
   3. 回来执行过滤器放行代码下边的资源

3. 过滤器的生命周期

   1. 在服务器启动后，会创建Filter对象，然后调用init方法  init和destory方法只会执行一次    init用于加载资源，destroy用于释放资源
   2. doFilter方法每一次请求被拦截资源时，会执行，执行多次
   3. destroy：在服务器关闭后，Filter对象被销毁。如果服务器是正常关闭，则会执行destroy方法，只执行一次用于释放资源

4. 过滤器配置详解

   1. **拦截路径的配置**

      1. 具体的资源路径    eg:  /index.jsp 只有访问index.jsp资源时，过滤器才会被执行
      2. 目录拦截：       eg:/user/*   访问/user下的所有资源时，过滤器都会被执行
      3. 后缀名拦截    eg：  *.jsp   访问多有后缀名为jsp资源时，过滤器都会被执行   * 是通配符    *.do , *.html.......
      4. 拦截所有资源  /*   访问所有资源时，过滤器都会被执行

   2. **拦截方式的配置** ：资源被访问到的方式

      1. 注解配置   

         * 设置dispatcherTypes属性

           1. REQUEST: 默认值  。  浏览器直接请求资源

              eg:@WebFilter(value = "/*",dispatcherTypes = DispatcherType.REQUEST)     或者在web.xml的filter mapping里面加上这句配置

           2. FORWARD:： 转发访问资源

           3. INCLUDE:包含访问资源

           4. ERROR:错误跳转资源

           5. ASYNC:异步访问资源

      2. web.xml配置

5. **过滤器链（配置多个过滤器）**

   * **执行顺序的问题**：如果有两个过滤器过滤器1和过滤器2
     1. 过滤器1
     2. 过滤器2
     3. 资源执行
     4. 过滤器2
     5. 过滤器1
   * 过滤器先后顺序问题
     1. 注解配置：按照类名的字符串比较规则比较，值小的先执行
        1. eg: AFilter  和 BFilter   A<B 所以AFilter先执行
     2. web.xml配置：  <filter-mapping>谁定义在上边儿谁先执行





案例：  1.登录验证【权限控制】

1. 访问day17—case的资源，验证其是否登录
2. 如果登录了，则直接放行
3. 如果没有登录，则跳转到登录页面，提示“您尚未登录，请先登录”



首先要排除是否是登录相关的资源     【防止出现死循环】

* 是，直接放行
* 不是：判断是否登录

操作：  创建LoginFilter  判断当前用户是否登录，判断Session中是否有User

* 有：已经登录了，放行
* 没有，没有登录，设置msg信息，跳转到登录页面



具体操作步骤

1.获取资源请求路径的URI   要用到  httprequest中的   req  所以首先应该强转，将servletrequest强转为  httpservletrequest

```java
//此代码不全，没有排除页面所使用的静态资源以及验证码的校验
HttpServletRequest request=(HttpServletRequest)req;
String uri = request.getRequestURI();
if(uri.contains("/login.jsp")||uri.contains("/loginServlet")){
    //包含，用户就是想去登录的，直接放行
    chain.doFilter(req, resp);
}
else {
    //不包含，需要验证用户是否登录
    //从session中获取user
    Object user = request.getSession().getAttribute("user");
    if(user!=null){
        //登录了，放行
        chain.doFilter(req, resp);
    }else{
        //没有登录，设置信息，返回至登录页面
        request.setAttribute("login_user","您还没有登录，请先登录");
        request.getRequestDispatcher("/login.jsp").forward(request,resp);
    }
}
```

**注意**：  在排除掉这两个uri地址之外 ，还应该对静态资源进行排除，否则在输入其它连接后  主页面没有样式 图片、css样式、js、验证码的servlet

```java
if(uri.contains("/login.jsp")||uri.contains("/loginServlet")||uri.contains("/css/")
        ||uri.contains("/js/")||uri.contains("/fonts/")||uri.contains("checkCodeServlet"))
```

```java
HttpServletRequest request=(HttpServletRequest)req;
String uri = request.getRequestURI();
if(uri.contains("/login.jsp")||uri.contains("/loginServlet")||uri.contains("/css/")
        ||uri.contains("/js/")||uri.contains("/fonts/")||uri.contains("checkCodeServlet")){
    //包含，用户就是想去登录的，直接放行
    chain.doFilter(req, resp);
}
else {
    //不包含，需要验证用户是否登录
    //从session中获取user
    Object user = request.getSession().getAttribute("user");
    if(user!=null){
        //登录了，放行
        chain.doFilter(req, resp);
    }else{
        //没有登录，设置信息，返回至登录页面
        request.setAttribute("login_user","您还没有登录，请先登录");
        request.getRequestDispatcher("/login.jsp").forward(request,resp);
    }
}
```





**案例2**：过滤敏感词汇

1. day17_case案例录入的数据进行敏感词汇过滤
2. 敏感词汇参考《敏感词汇.txt》   笨蛋、坏蛋
3.  如果是敏感词汇，替换为  ***

实现：

1. 对request对象进行增强，增强获取参数相关方法即可
2. 放行。 传递代理对象



分析： 

1. 在过滤器中， 对request对象进行增强     request的getparameter方法去获取值，然后将其赋值给一个新的request对象中，然后在放行时将新的request传入【对request对象进行增强】

2. 增强对象的功能

   * **设计模式**：一些通用的解决固定问题的方式

     1. 装饰模式

     2. **代理模式**          举例：  人  第三方商城代理联想电脑公司     直属对象：联想公司

        * 概念：
          1. 真实对象：被代理的对象   【直属对象】 
          2. 代理对象：第三方商城
          3. 代理模式：代理对象代理真实对象，达到增强真实对象功能的目的

        * 实现方式     **基于的点是  在servlet和filter拿到的requset对象是同一个**    ， 可以在servlet 和   dofilter对request进行打印，会输出其地址值，看是否一样

          1. 静态代理：有一个类文件描述代理模式
          2. 动态代理：在内存中形成代理类
             * 实现步骤：
               1. 代理对象和真实对象实现相同的接口
               
               2. 代理对象 = Proxy.newProxyInstance();
                  * 三个参数： 真实对象的类加载器，真实对象的接口数组 ，处理器  ：new  IncocationHandler();
                  
               3. 使用代理对象调用方法
               
               4. 控制方法来增强方法
               
                  首先创建一个代理接口，写入代理的方法，然后创建一个真实对象的java的类，实现这个接口， 创建ProxyTest去测试，写入    newProxyInstance的三个参数是：  第一个：真实对象的类加载器 第二个：真实对象的接口的数组  第三个：新建了一个处理器  【这三个参数相对固定】
               
                  le.getClass().getClassLoader(), le.getClass().getInterfaces(), new InvocationHandler()
               
                  invoke方法中的三个参数：
               
                  * proxy:代理对象
                  * method:代理对象调用的方法，被封装为的对象
                  * args:代理对象调用方法时，传递的实际参数
          
          ```java
          //1.创建真实对象
                  lenovo le = new lenovo();
                  //2.动态代理增强le对象
                  SaleComputer proxy_lenovo = (SaleComputer) Proxy.newProxyInstance(le.getClass().getClassLoader(), le.getClass().getInterfaces(), new InvocationHandler() {
                      //代理逻辑编写的方法，代理对象调用的所有方法都会触发该方法执行
                      @Override
                      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                          return null;
                      }
                  });
          
          ```

因为代理对象和真实对象使用了相同接口，所以将其都强转为 接口对象

  

eg:  1.完整案例 创建SaleComputer接口

```java
public String sale(double money);
```

2.创建真实对象的类  Lenovo   实现接口，覆写方法

```java
@Override
public String sale(double money) {
    System.out.println("使用了"+money+"购买了aaaaaaa电脑");
    return "方法执行了aaaa";
}
```

3.创建ProxyTest  作为测试类

```java
   //1.创建真实对象
        final Lenovo le = new Lenovo();
        //2.动态代理增强le对象
        SaleComputer proxy_lenovo = (SaleComputer) Proxy.newProxyInstance(le.getClass().getClassLoader(), le.getClass().getInterfaces(), new InvocationHandler() {
            //三个参数：  proxy:代理对象     method:代理对象调用的方法，被封装为的对象
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                System.out.println("代理对象执行了方法");
//                return "aa方法";
//                System.out.println(method.getName());//下边调用的方法
//                System.out.println(args[0]);
                Object obj = method.invoke(le,args);
                return obj;
            }
        });
        String comsale = proxy_lenovo.sale(8000);
        System.out.println(comsale); //这个时候，代理对象就会和真实对象执行一样的方法 了
```

方法的增强，增强点在三方面

* 增强参数列表
* 增强返回值类型
* 增强方法体逻辑





Listener：监听器

* 概念：web的三大组件之一
* * 事件监听机制
  * 事件 ： 一件事情
  * 事件源：事情发生的地方
  * 监听器：一个对象
  * 注册监听：将事件、事件源、监听器绑定在一起，当事件源上发生某个事件后，执行监听器代码



* ServletContextListener  :监听ServletContext对象的创建和销毁

  * void  contextDestroyed(ServletContextEvent sce):ServletContext对象被销毁之前会调用该方法
  * void ContextInitialized(ServletContextEvent sce):ServletContext对象创建后会调用此方法

* **使用步骤**

  1. 定义一个类，实现ServletContextListener接口

  2. 复写方法

  3. 配置

     1. web.xml配置

        <listener>

        ​	<listener-class>全类名</listener-class>

        </listener>

     2.  注解配置       @webListener