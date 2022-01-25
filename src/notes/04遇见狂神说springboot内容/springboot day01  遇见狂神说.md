# springboot day01  遇见狂神说

springboot的核心就是  自动装配        ==约定大于配置==

程序=数据结构+算法



微服务结构  



（1）搭建第一个Springboot项目

官方，提供了一个快速开发的网站，idea集成了这个网站

https://start.spring.io/

在里面快速搭建    点击生成，会下载一个压缩包

（2） 直接在idea创建

 



结构

在java下有一个application  程序的主入口

会运行出程序的 banner

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

在 application程序主入口的同级中创建  controller等包文件

在控制台输出 hello,word     就是在controller包中创建一个类，在类上添加注解@RestController

在输出方法上 加上RequestMapping("/路径名")



```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

spring-boot-starter-web用于实现http接口，（该依赖中包含了Spring MVC）,官网对他的描述是：使用Spring MVC构建Web(包括RESTful应用程序的入门者，使用Tomcat作为默认嵌入式容器)

spring-boot-starter-test用于编写单元测试的依赖包 



使用maven的 package命令打包    进入其目录 使用 WindowPowerShell命令  进入到项目目录，然后    使用       java -jar jar包名字的命令运行





创建项目后修改端口号 

resources 目录下 application.properties   打开输入  server.port=新端口号



更改banner

在ersources下  新建    banner.txt  直接拷贝从网页上下载到的banner进去即可



### **自动配置的原理**

* **pom.xml**中存放了其核心依赖，在父工程中的parent里面，
* 我们在写或者引入一些springboot依赖的时候，不需要指定版本，就因为有这些版本仓库
* springboot会将所有的功能场景，都变成一个个的启动器
* 如果我们要使用什么功能，就只需要找到对应的启动器即可



主程序：

```java
@SpringBootApplication  //标注这个类式一个springboot的应用
public class Springbootdemo01Application {

    public static void main(String[] args) {
        SpringApplication.run(Springbootdemo01Application.class, args);//将springboot启动
    }

}
```

**主程序的注解点开后**

* ```
  @SpringBootConfiguration       springboot的配置
  	@Configuration             spring配置类
  		@Component              说明这也是一个spring的组件
  @EnableAutoConfiguration        自动配置
  	@AutoConfigurationPackage    自动配置包
  		@Import({Registrar.class}) 自动配置‘包注册’
  		=@Import(AutoConfigurationPackage.Registrar.class)
  	@Import({AutoConfigurationImportSelector.class})自动配置导入选择 
  ```



@AutoConfigurationImportSelecto

```java
//获取所有的配置
List<String> configurations = getCandidateConfigurations(annotationMetadata, attributes);

getCandidateConfigurations获取候选的配置的方法
```



**总结：**springboot所有自动配置都是在启动的时候扫描并加载：‘spring.factories’所有的自动配置类都在这里面，但是不一定生效，要判断条件是否成立，只要导入了对应的start，就有对应的启动器了，有了启动器，自动装配就会生效，然后就配置成功

1. springboot在启动的时候，从类路径下 /META-INF/spring.factories获取指定的值；
2. 将这些自动配置的类导入容器，自动配置就会生效，来帮我们进行自动配置
3. 以前我们需要自动配置的东西，现在springboot帮我们做了
4. 整合javaEE,解决方案和自动装配的东西都在 spring-boot-autoconfugure-2.2.0.RELEASE.jar这个包下
5. 它会把所有需要导入的组件，以类名的方式返回，这些组件就会被添加到容器
6. 容器中也会存在非常多的 xxxAutoConfiguration的文件（@Bean），就是这些类给容器导入了这个场景需要的所有组件,并自动配置
7. 有了自动配置类，就免去了我们手动编写配置文件的工作了

springapplication.run分析  

分析该方法主要分为两部分 ， 一部分是SpringApplication的实例化，二是run方法的执行

springapplication这个类做了四个事情

1. 推断应用的类型式普通的项目，还是web项目
2. 查找并加载所有可用初始化值，设置到initializers属性中
3. 找出所有的应用程序监听器，设置到listeners属性中
4. 推断并设置main方法的定义类，找到运行的主类





springboot   核心是 自动装配 







### **yaml语法讲解**



springboot的配置文件

其中可以配置的有：

官方的配置太多了，了解原理 





首先创建好springboot项目后，把application.properties 文件删除，创建为application.yaml文件

springboot使用一个全局的配置文件，配置文件名称是固定的

application.properties【properties 只能配置键值对】

* 语法结构： key=value

**application.yaml**【对空格的要求很高】

【其中的配置可以注入到我们的配置】

**yaml可以直接给实体类赋值**

* 语法结构：  key:空格value

**配置文件的作用：**修改springboot的自动配置的默认值，因为springboot在底层都会给我们自动配置好了

yaml是以数据为中心的

xml配置

```xml
<server>

​	<port>8080</port>

</server>
```

```yaml
server:
  port: 8081
```



yaml可以存储键值对

也可以存储对象

```
Student:

​	name: ds

​	age: 3
```

yaml的行内写法

Student: {name: ds,age: 3}

yaml数组：

```yaml
Pets:

​	-cat

​	-dog

行内写法：  Pets: [cat,dog,pig]
```





新建一个实体类  Person

在上面加上@Component  

再加上ConfigurationProperties(prefix= "yaml文件中的名字参数")



测试的时候，在测试类中  使用@Autowired注解 注入Person类，将实体类加载进来 值注入进来





application.yaml可以配置的地方有

项目路径下  创建config文件 里创建application.yaml

项目的根路径下创建application.yaml

resources  下创建config文件  创建application.yaml

resources  下创建application.yaml

项目目录下的config中的 yaml优先级最高  项目的根路径下 yaml第二

resources  下创建config文件  优先级第三     resources  下创建application.yaml优先级最低







**多环境切换:**

**方式一**

创建： application.properties      application-test.properties      application-dev.properties

springboot的多环境配置，可以选择激活哪一个配置文件【在application.properties写上下面的】

spring.profiles.active=dev/test【写上-后面的就可以了】

**方式二**

在application.yaml中使用     ---        分隔开

eg:

```
server:

​	port:8080
spring:
	profiles:
		active: dev    //这三行是调用开发环境配置文件
---
server:

​	port:8081
spring:
	profiles: test     //这两句是给环境定义一个名字
---
server:

​	port:8082
spring:
	profiles: test
```





**分析自动配置原理**

配置文件到底能写什么东西？-----------spring.factories

在配置文件中能配置的东西，都存在有一个固有的规律

xxxProperties   【这个文件要被】xxxAutoConfiguration 【自动装配】

xxxProperties  可以和配置文件绑定，我们就可以使用自定义的配置了 



### 自动装配的原理

**精髓**  **重点**

1. springboot启动会加载大量的自动配置类

2. 我们看我们需要的功能有没有在springboot默认写好的自动配置类当中

3. 我们再来看这个自动配置类中到底配置了哪些组件；（只要我们要用的组件存在在其中，我们就不需要再手动配置了）

4. 给容器中自动配置类添加组件的时候，会从properties类中获取某些属性，我们只需要在配置文件中指定这些属性的值即可

   xxxAutoConffiguration :  自动配置类；  给容器中添加组件

   xxxProperties:封装配置文件中相关属性





可以通过 **debug: true**来查看，哪些自动配置类生效，哪些没有生效

在配置类上写上          **debug: true**

Positive  matches     就是生效的 开启的 匹配成功的

Negatice  matches     没有生效，没有被匹配成功的

​    

