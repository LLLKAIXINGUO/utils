# day22 第一部分 ajax 和json

### Ajax ：

* 是一种用于创建快速动态网页的技术
* 是一种在无需重新加载**整个**网页的情况下，能够更新**部分**网页的技术
* 通过在后台与服务器进行少量数据交换，Ajax可以使网页实现异步刷新。这意味着可以在不重新加载整个网页的情况下，对网页的某部分进行刷新

### *  概念：  异步的JavaScript 和 xml 

* 异步和同步： 客户端和服务器端相互通信的基础上
  * 同步：客户端必须等待服务器端的响应。在等待的期间客户端不能做其他操作
  * 异步：客户端不需要等待服务器端的响应，在服务器处理请求的过程中，客户端可以做其他的操作



* **实现方式**

  1. 原生的js实现方式（所用较少，了解）

     * 创建一个web项目，创建页面  在页面中<input type="button" value="发送异步i请求" onclick="fun();">
     * 在javascript代码中

     

  2. jquery实现方式

     1. **$.ajax()  :通用的方式**

        * 语法：  $.ajax({键值对})；

        * 键值对包括的键有 ： 请求路径  请求方式  请求参数......

          * eg:     url:"ajaxServlet", type:"POST", 【type不写默认是get方式请求】，data:"username=jack&age=23" ||data:{"username":"jack","age":23},success:function(){//响应成功后的回调函数}

        * ```javascript
          function fun(){
              $.ajax({
                  url:"ajaxServlet",
                  type:"POST",
                  data:{"username":"jack","age":20},
                  success:function (da){
                      //响应成功后的回调函数
                      alert(da);
                  },
                  error:function (){
                      //响应失败后的回调函数
                      alert("请求错误");
                  }
              });
          }
          ```

        

     2. **$.get()：发送get请求**

        * 语法：$.get(url,[data],[callback],[type])   

          * 参数:url ： 请求路径是必须的，其他几个是非必须的

          * 参数data: 请求参数

          * callback:回调函数

          * type:响应回去的数据格式

          * eg:      function fun(){

          * ```javascript
            * $.get("ajaxServlet",{"username":"jack","age":20,"sex":"nan"},function(参数){}，“//数据格式 text”)
            * }
            ```

            

     3. ** **

        **$.post()：发送post请求**

### JSON:         javascript对象表示法

概念：        javascript对象表示法

json是存储和交换文本信息的语法。类似xml

json比xml更小，更快，更易解析

eg: 在java中定义一个对象 

```
Person p =  new  Person();

p.serName("张")；

p.setAge(16);

p.setGender("男")
//java中的对象格式
```

javascript的   var  p  ={"name":"张"，“age”:16,"gender":"男"}；



### **语法**

1. **基本规则**
   * 数据在名称/值对中：json数据是由键值对构成的
     * 键用引号(单双都行)引起来，也可以不使用引号
     * 值的取值类型
       * 数字（整数或浮点数）
       * 字符串（在双引号中）
       * 逻辑值（true或false）
       * 数组（在方括号中）  {"person":[{},{}]}
       * 对象（在花括号中）  {"address":{"province":"陕西"}}
       * null
   * 数据由逗号隔开
     * 多个键值对由逗号分隔
   * 花括号保存对象：使用{}保存json格式
   * 方括号保存数组：[]

定义基本格式：var person = {"name":"张三","age":20,gender:true};

定义嵌套格式：

```javascript
{}----------》[]
var persons ={"persons":[{"name":"张三","age":20,gender:true},{"name":"李四","age":18,gender:true}]}
[]---------->{}
var ps = [{"name":"张三","age":20,gender:true},{"name":"李四","age":18,gender:true}]
```

2.**获取数据**

* json对象.键名
* json对象["键名"]
* 数组对象[索引]



```json
var person = {"name":"张三","age":30,gender:true};
//使用for  in的方式遍历
for(var key in person){
    alert(key+"  "+person[key]);
}
```

```json
var ps = [{"name":"张三","age":20,gender:true},{"name":"李四","age":18,gender:true}];
//使用双层for循环遍历
 for (var i=0;i<ps.length;i++) {
            var p=ps[i];
            for(var key in p){
                alert(key+"  "+p[key])
            }
        }
```

**3.json数据和java对象的相互转换**

* json解析器   ： Jsonlib \  Gson     fastjson     **jackson** :springmvc内置de

* **java转为json对象**
  1. 导入jackson的坐标依赖

```xml
<!--   jackson的依赖坐标-->
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.13.0</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core -->
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-core</artifactId>
      <version>2.13.0</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations -->
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-annotations</artifactId>
      <version>2.13.0</version>
    </dependency>
```

​	2.创建jackson核心对象  ObjectMapper

3. 调用ObjectMapper的相关方法进行转换
   * writeValue(参数1，obj):
     * 参数1
       * File  :将obj对象转换为JSON字符串，并保存到指定的文件中
       * Writer:将obj对象转换为JSON字符串，并将JSON数据填充到字符输出流中
       * OutputStream:将obj对象转换为JSON字符串，并将JSON数据填充到字节输出流中
   * writeValueAsString(obj): 将对象转为json字符串

* **json对象装为java**

  1. 导入jackson的坐标依赖

  2. 创建jackson核心对象  ObjectMapper

  3. 调用ObjectMapper的相关方法进行转换       

     * readValue(json字符串数据，Class)

     * ```java
       @Test
       public void test05() throws JsonProcessingException{
           String json = "{\"name\":\"张三\",\"age\":16,\"gender\":true,\"birthday\":null}";
           ObjectMapper mapper = new ObjectMapper();
           Person person = mapper.readValue(json, Person.class);
           System.out.println(person);
       
       }
       ```



**注解**

1. ​	@JsonIgnore:排除属性                  加上这个注解，转json时候，忽略这个属性
2. @JsonFormat:  属性值的格式化      在实体类中添加 加上这个注解，转json时候，改变其格式



3. 复杂格式转换

```
@Test
public void test03() throws JsonProcessingException {
    Person person = new Person();
    person.setName("张三");
    person.setAge(16);
    person.setGender(true);
    person.setBirthday(new Date());

    Person person1 = new Person();
    person.setName("张三");
    person.setAge(16);
    person.setGender(true);
    person.setBirthday(new Date());


    Person person2 = new Person();
    person.setName("张三");
    person.setAge(16);
    person.setGender(true);
    person.setBirthday(new Date());

    ArrayList<Person> ps = new ArrayList<>();
    ps.add(person);
    ps.add(person1);
    ps.add(person2);

    ObjectMapper mapper = new ObjectMapper();
    String s = mapper.writeValueAsString(ps);
    System.out.println(s);

}
```

```
//输出结果
[{"name":"张三","age":16,"gender":true,"birthday":"2021-10-10"},{"name":null,"age":0,"gender":false,"birthday":null},{"name":null,"age":0,"gender":false,"birthday":null}]
```





```java
@Test
public void test04() throws JsonProcessingException{
    Map<String,Object> map = new HashMap<String, Object>();
    map.put("名字","zhang");
    map.put("年龄",20);
    map.put("性别","男");
    ObjectMapper mapper = new ObjectMapper();
    String s = mapper.writeValueAsString(map);
    System.out.println(s);

}
```





* 案例   判断用户名是否已经注册
