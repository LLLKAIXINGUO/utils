# 尚硅谷springboot2.0 day02 

pom.xml中的依赖

加入这个依赖会在yaml中输入实体类.会出现实体类的属性的提示

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
</dependency>
```



### **web开发**

  创建项目后

1.1 静态资源访问

			* 静态资源目录 
			* static     public       resources            META-INF/resources
			* 静态映射 /** 
			* 请求进来后，先去找Controller看能不能处理，不能处理的所有请求又都交给静态资源处理器，静态资源处理器也找不到就返回404 

* 静态资源配置  ： 让拦截器配置方便，， 让所有的静态资源都带一个前缀的  

* 静态资源访问前缀（默认无前缀）

  ```yaml
  spring:
    mvc:
      static-path-pattern: /res/**
  ```



1.2 欢迎页支持 

* 1.给静态资源下放一个index.html     不可以配置静态资源的访问前缀，否则会导致index.html不能被默认访问
* 2.在controller中 定义要给 跳转到index的请求

1.3 自定义favicon.ico  小图标

	*  把图片起这个名字后，放入静态资源文件下
	*  不可以配置静态资源的访问前缀，否则会导致endex.html不能被默认访问



## 静态资源配置原理

* springboot启动默认加载   xxxAutoConfiguration类（自动配置类）
* springmvc功能的自动配置类 WebMvcAutoConfiguration,生效



Rest风格支持:(使用HTTP请求方式动词来表示对资源的操作)

* 以前 /getUser(获取用户)      /deleteUser 删除用户  、/editUser  修改用户    /saveUser保存用户

* 现在  /user   GET 获取用户 DELETE 删除用户  PUT修改用户  POST保存用户

* **核心Filter：** HiddenHttpMethodFilter

  * 用法 ： 表单method=post,隐藏域_method=put     <input name="_method" type="hidden" value="put">

  * 然后在配置文件中手动开启 

    ```yaml
    spring:
      mvc:
        hiddenmethod:
          filter:
            enabled: true
    ```

  * REST提交（**原理**） 表单在提 交时候，会自动的带上 _method的参数

```java
@RequestMapping(value = "/user",method = RequestMethod.GET)
public String getUser(){
    return "GET-张三";
}

@RequestMapping(value = "/user",method = RequestMethod.POST)
public String saveUser(){
    return "POST-张三";
}

@RequestMapping(value = "/user",method = RequestMethod.PUT)
public String putUser(){
    return "PUT-张三";
}

@RequestMapping(value = "/user",method = RequestMethod.DELETE)
public String deleteUser(){
    return "DELETE-张三";
}
```

```html
<form action="/user" method="get">
    <input value="REST-GET 提交" type="submit"/>
</form>

<form action="/user" method="post">
    <input value="REST-POST 提交" type="submit"/>
</form>

<form action="/user" method="post">
    下边的这句话
    <input name="_method" type="hidden" value="put">
    <input value="REST-POST 提交" type="submit"/>
</form>
<form action="/user" method="post">
    <input name="_method" type="hidden" value="delete">
    <input value="REST-POST 提交" type="submit"/>
</form>
```

## 常用注解使用

**传参的几种**

@RequestParam传入参数

HttpSession session

```java
@GetMapping("/car/{id}/owener/{name}")
public Map<String,Object> getCar(@PathVariable("id")Integer id,
                                 @PathVariable("name")String name,
                                 @PathVariable Map<String,String> pv){
    Map<String,Object> map = new HashMap<>();
    map.put("id",id);
    map.put("name",name);
    map.put("pv",pv);
    return map;
}
```

```json
{"pv":{"id":"1","name":"李四"},"name":"李四","id":1}
```

```java
@GetMapping("/car/{id}/owener/{name}")
public Map<String,Object> getCar(@PathVariable("id")Integer id,
                                 @PathVariable("name")String name,
                                 @PathVariable Map<String,String> pv,
                                 @RequestHeader("User-Agent") String userAgent,     //可以查到请求头
                                 @RequestHeader Map<String,String> header)
//拿到所有的请求数据{
    Map<String,Object> map = new HashMap<>();
    map.put("id",id);
    map.put("name",name);
    map.put("pv",pv);
    map.put("userAgent",userAgent);
    map.put("header",header);
    return map;
}
```

```json
{"pv":{"id":"1","name":"李四"},"name":"李四","header":{"host":"localhost:8080","connection":"keep-alive","cache-control":"max-age=0","sec-ch-ua":"\"Google Chrome\";v=\"95\", \"Chromium\";v=\"95\", \";Not A Brand\";v=\"99\"","sec-ch-ua-mobile":"?0","sec-ch-ua-platform":"\"Windows\"","upgrade-insecure-requests":"1","user-agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36","accept":"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9","sec-fetch-site":"none","sec-fetch-mode":"navigate","sec-fetch-user":"?1","sec-fetch-dest":"document","accept-encoding":"gzip, deflate, br","accept-language":"zh-CN,zh;q=0.9","cookie":"Idea-fe704f3c=19ae2c3e-a7e1-433e-b3c5-d0418da7c779; Idea-a03528cc=4cfc78c6-51a5-47c3-810d-fb42ef84be68; Idea-a03528cd=d01a2b47-eac5-4b0d-984e-66268c13d3eb"},"userAgent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36","id":1}
```

@Requestbody  获取{post}请求体数据



@RequestAttribute获取请求域中的值