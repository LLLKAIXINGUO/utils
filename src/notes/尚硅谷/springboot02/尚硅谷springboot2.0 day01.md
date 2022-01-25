# 尚硅谷springboot2.0 day01

### springboot官方文档架构

打开spring.io      ---->project-------->springboot

微服务是一种架构风格 。 服务围绕业务功能拆分，可以由全自动部署机制独立部署，去中心化，服务自治，服务可以使用不同的语言，不同的存储技术



1. 创建  普通maven项目，加入依赖

```xml
<parent>
        <artifactId>spring-boot-dependencies</artifactId>
        <groupId>org.springframework.boot</groupId>
        <version>2.5.5</version>
    </parent>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

2. 创建启动类 MainApplication  在类上加入注解 @SpringBootApplication  标注这个类是主程序类

```java
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}
```

3. 写controller类  
4. 运行主程序类的main方法

```java
SpringApplication.run(MainApplication.class,args);
```



```java
//1.返回我们的ioc容器
ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);
//2.查看我们容器里面的组件
String[] names = run.getBeanDefinitionNames();
for (String name : names) {
    System.out.println(name);
}
//查看容器中组件的数量
int count = run.getBeanDefinitionCount();
System.out.println(count);
```



给springboot添加组件到容器中

```java
@Configuration
MyConfig类
@Bean
public Person person(){
    return new Person("张三",15,true);
}
@Bean("tom") //可以自定义组件名字
public Pet cat(){
    return new Pet("咖啡",19);
}
```

**使用springboot的注解**

1. @Configuration   给类上添加一个这个注解 ，， 告诉springboot这是一个配置类，等同于配置文件

2. @Bean  注解，给容器中添加组件，，，以方法名作为组件的id，返回类型就是组件类型，方法返回的值就是组件在容器中的实例，配置类里面使用@Bean标注在方法上给容器注册组件，默认也是单例的

3. 外部无论是对配置中的组件注册方法调用多少次，获取的都是之前注册容器中的单实例

   ```java
   Pet tom = run.getBean("tom", Pet.class);
   Pet tom1 = run.getBean("tom", Pet.class);
   Pet tom2 = run.getBean("tom", Pet.class);
   System.out.println(tom);  //        Pet{name='咖啡', age=19}
   System.out.println(tom1);  //        Pet{name='咖啡', age=19}
   System.out.println(tom2);  //        Pet{name='咖啡', age=19}
   System.out.println("组件："+(tom==tom1));   //        组件：true
   ```



4. @import({User.class,....})  注解       可以通过这个容器，给容器中导入多个组件   可以自动的给容器中创建出括号中类型的组件          @import导入的组件，默认组件的名字就是全类名

5. @Conditional  条件装配：满足Conditional  的条件，就进行组件注入

6. 只有在容器中的组件，才会拥有springboot提供的强大功能

7. @springbootapplication注解          启动类的注解

   1. ```
      @springbootapplication注解中的 @EnableAutoConfiguration（重点） 
      ```

```
@Import(AutoConfigurationPackages.Registrar.class)
import注解的高级用法，利用Registrar给容器中导入一系列的组件
将指定的一个包下的所有组件导入进来
```



#### 自动配置



## springboot的最佳实践

* 引入场景依赖

* 查看自动配置了哪些(选做)

  * 自己分析，引入场景对应的自动配置一般都生效了
  * 在application.properties  文件中加入  debug=true  开启debug模式

* 是否需要修改

  * 参照文档修改配置项
  * 自定义加入或者替换组件
    * @Bean  @Component......

  ### 开发小技巧

  1. #### lombok简化开发

  2. ### **dev-tools**           热部署更新

     ```
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-devtools</artifactId>
         <optional>true</optional>
     </dependency>
     部署后，使用  ctrl+f9就可以了完成重新编译重新加载
     ```

  3. ### **Spring  Initailizr**（项目初始化向导）

     创建springboot项目时候用到的



## yaml文件的用法

key: value （注意其中的空格）     使用缩进来表示层级关系

​       '#'表示注释























```html
<script>
    var json = {
        "name":"张三",
        "age":22,
        "list":["s1","s2","s3"],
        "map":{"key_1":"value1","key_2":"value2"}
    }
    alert("json");
    alert(json.age);
    alert(json.list);
    for(var i=0;i<json.list.length;i++){
        alert(json.list[i]);
    }
    alert(json.map.key_1);
    alert(json.map.key_2);
</script>
```

 

json  本身是一个对象 

json中的key可以理解为是对象中的一个属性，json 中的key访问



json存在有两种形式：

一种是对象的形式：  我们叫它 json对象

一种是字符串的形式存在，我们叫它json字符串



一般我们要操作json中的数据的时候，需要json对象的格式

一般要在客户端和服务器之间进行数据交换的时候，使用json字符串



JSON.stringify() 把json对象转换成为json字符串

JSON.parse()  把json字符串转换为json对象 





