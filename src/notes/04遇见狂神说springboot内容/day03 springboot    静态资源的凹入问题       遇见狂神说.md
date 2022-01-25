# day03 springboot    静态资源的凹入问题       遇见狂神说



**在templates目录下的所有页面，只能通过controller来跳转**

这个需要模板引擎的支持



模板引擎：只要使用thymleaf，就只需要导入依赖即可



使用thymleaf

1. 第一步，导入它的命名约束 <html xmlns:th="http://www.thymeleaf.org">





springboot扩展springmvc操作     在类上加上  @Configuration

创建一个类，实现 WebMvcConfigurer接口 ，  然后需要扩展什么就是实现哪个方法



员工管理系统实现

1. 创建一个springboot项目

2. 导入静态资源 ，页面给templates里，静态资源放入static

3. 不用数据库，伪造数据库

4. ```
   @Repository  dao层加上这个注解，表示被spring托管了
   ```





```thymeleaf
<p th:if="${not #strings.isEmpty(msg)}" style="color: red"  th:text="${msg}"></p>    如果没有登录进去，后端model回一个msg, 判断msg是否为空，不为空则显示出来
```



登录拦截器， 让没有进行登录的重定向到登录页面，不能访问后台页面

1. 创建一个类，实现HandlerInterceptor 接口

2. ```java
   //重写接口中的方法
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       //登录成功之后，获取到用户的session存入的名字
       Object loginuser = request.getSession().getAttribute("loginuser");
       if(loginuser==null){
           request.setAttribute("msg","您还没有登录，没有权限");
           request.getRequestDispatcher("/index.html").forward(request,response);
           return false;
       }else
           return true;
   }
   ```

3. 在springmvcconfig中注册进去

   ```java
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
               .excludePathPatterns("/index.html","/","/user/login","/css/**","/img/**","/js/**");
   }
   ```

4. 设置完成，重启项目



