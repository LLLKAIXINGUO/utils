# springboot day02  遇见狂神说 springboot web

* xxxxAutoConfiguration....向容器中自动配置组件
* xxxxProperties   自动配置类，装配配置文件中自定义的一些内容



**要解决的问题：**

* 导入静态资源   【无webapp目录】
* 首页
* jsp,模板引擎Thymeleaf
* 装配和扩展SpringMVC
* 增删改查
* 拦截器
* 国际化【扩展内容】





**导入静态资源：**       默认静态资源放置位置

可以访问到    resources目录下的 创建的public文件夹 static 文件夹  templates文件夹，  以及resources文件夹下创建的resources文件夹目录

resources目录下资源的访问优先级最高，static其次， public 最低

【在三个文档下都创建一个1.js里面加文字，就可以测试到】



首页 定义一个index.html  放到 resources下    或者 resources下的static、public、resources下  

**注意：**templete下的页面只能通过controller来跳转 ， ，还需要模板引擎的支持



# **thymeleaf模板引擎 使用：**

1. 先引入thymeleaf的依赖 

   ```xml
   <dependency>
       <groupId>org.thymeleaf</groupId>
       <artifactId>thymeleaf-spring5</artifactId>
   </dependency>
   <dependency>
       <groupId>org.thymeleaf.extras</groupId>
       <artifactId>thymeleaf-extras-java8time</artifactId>
   </dependency>
   ```

2.在template下定义html页面   eg：test.html

3.在controller包下  写方法    return到所定义的html页面    return "test"



在html中 

1. 第一步，导入约束       lang="en"后  xmlns:th="http://www.thymeleaf.org"

简单的表达式

${name}          取变量

*{...}               选择的表达式 

 /          #{...}             消息

@{ }                     URL

 定义完了后   访问时候 <div th:text="${msg}"> </div>     使用th表达式

所有的 html元素都可以被thymeleaf替代接管：              th：元素名

#### thymeleaf的语法

```java
@Controller
public class Demo01testController {
    @RequestMapping("/test")
    public String tiao(Model model){
        model.addAttribute("msg","hello,springboot");
        model.addAttribute("users", Arrays.asList("aaa","bbb","cccc"));
        return "test";
    }
}
```



```html
<div th:text="${msg}"> </div>
<hr>
<div th:each="user:${users}" th:for="${user}"></div>
将其遍历 的结果赋值给user,    再通过后面的语句访问出来
<div th:each="user:${users}" >[[ ${user} ]]</div> 这两条语句等价，效果一样
```



**条件判断表达式**

if-then     :（if）? (then)

if-then-else :    (if)？（then）:(else)

Default      :  (value)?:(defaultvalue)





### mvc配置原理 【重难点】

 **配置类 一般放在 config包里**

1.创建config包  -------》创建类  SpringMvcConfig          在类上使用注解@Configuration【这个注解是让类变成配置类】

2.这个类的类型必须是WebMvcConfigurer的   实现这个接口

 在类中写功能【实现方法 alt+insert】 eg:视图跳转 

```java
@Override
public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/lym").setViewName("/test");
} 即：访问lym路径，跳转到test路径的页面
```

**全面接管即：SpringBoot对SpringMVC的自动配置不需要了，所有都是我们自己去配置！**

**只需在我们的配置类中要加一个@EnableWebMvc。**







springbootdemo03

创建一个项目，导入   thymeleaf的依赖

然后 把template      和       static里面导入静态页面资源

创建pojo包      Department   、Employee

创建dao包      DepartmentDao 、 EmployeeDao 



改造html页面    

首先在头文件 加入 xmlns的链接



1. 配置静态资源的路径             使用thymeleaf  th:         @{}

2. 配置国际化     在resources下  创建     i18n文件    【首字母是i尾字母是n中间有18个字母 】 在其中   创建文件   login.properties  和  login_zh_CN.properties  创建完了以后，会自动把这两个配置文件放入进去一个配置文件中   点击自动生成的配置文件 点击添加，输入   en_US就会自动添加进去了英文的配置文件

3. 点击生成的目录  ，，然后左下角的  Text/Resource Bundle 点击Resource Bundle  可视化配置     然后配置出现的英文

4. 进入到html页面中， 加入      thymeleaf           th:     #{}

5. 创建 config目录 在其中创建 MyLocaleResolver类实现  LocaleResolver接口

6. 在index.html页面中的中文英文超链接中加入   th:href="@{/index.html(l="zh_CN")}"         th:href="@{/index.html(l="en_US")}"

7. 

   ```java
   public class MyLocaleResolver implements LocaleResolver {
   
       //解析请求
       @Override
       public Locale resolveLocale(HttpServletRequest request) {
           //获取请求中的语言参数
           String language = request.getParameter("l");
           Locale locale = Locale.getDefault();//如果没有就使用默认的
           //如果请求的链接携带了国际化的参数
           if(!StringUtils.isEmpty(language)){
               //zh_CN
               String[] split = language.split("_");
               //国家 ， 地区
               locale =  new Locale(split[0],split[1]);
           }
           return locale;
       }
   
       @Override
       public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
   
       }
   }
   ```

   8. 这个国际区域化写完后。放到Bean里边

   9. 在springmvcconfig里 

   10. ```java
       //将自定义的国际化放到spring的bean里边，自定义的国际化组件就生效了
       @Bean
       public LocaleResolver localeResolver(){
           return new MyLocaleResolver();
       }          自定义的web 配置文件中
       ```

#####   **总结*：*  页面国际化

1. 我们需要配置  i18n文件
2. 我们如果需要在项目中进行按钮自动切换，我们需要定义一个组件LocaleResolver
3. 记得将自己写的组件配置到spring容器 @Bean
4.  /   #{}



**登录功能实现**

首先  将form表单中的action  修改为 th:action="@{/user/login}"格式

在controller里面新建  LoginController  写 一个方法 测试可不可以跳转到

加上@ResposBody  然后返回”ok“ 字符串     运行起来 随便输入点击登录试试看



在html页面中的  username       password标签中分别加入      name="username"     name="password" 在后端就可以进行接收了 



```html
//如果判断msg不为空则显示
<div style="color: red" th:text="${msg}" th:if="${not #strings.isEmpty(msg)}"></div>
```



```java
registry.addViewController("/main.html").setViewName("dashboard"); //webmvcconfig的addViewControllers加入
```

在登录的controller下 写入登录的业务判断         然后  return到重定向    return "redirect:/main.html"     实现登录后URL重定向到main.html



**登录拦截器**       防止被人直接通过/main进入系统内

  创建处理器拦截器配置类

LoginHandlerInterceptor

实现HandlerInterceptor接口        处理器拦截器

重写拦截器中的方法      三个方法中的        preHandle 方法      return true就是放行，return  false就是不放行 

在登录成功处，获取session        

```java
Object loginname = request.getSession().getAttribute("loginname");
if(loginname==null){ //如果没有登录
    request.setAttribute("msg","没有权限，请先登录");
    request.getRequestDispatcher("/index.html").forward(request,response);
    return false;
}
else
return true;             //在登录处理器拦截器中判断session是否有值，若无则转发至登录页面
```



```java
@Override
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginHandlerInterceptor()).
            addPathPatterns("/**").
            excludePathPatterns("/","/user/login","/index.html","/css/*","/js/*","/img/*");//拦截所有请求，除了这几个
}
```

在springmvcconfig中添加拦截器的实现方法  





## **增删改查  功能**

```java
//EmployeeController controller调用service层，这里调用dao是因为未写。
@Autowired
private EmployeeDao employeeDao;
@RequestMapping("/emps")        //页面的跳转链接  静态页面也是写这个
public String list(Model model){
    Collection<Employee> employees = employeeDao.all();
    model.addAttribute("emp",employees);
    return "empl/list";         //此路径为静态页面所在的路径
}
```

