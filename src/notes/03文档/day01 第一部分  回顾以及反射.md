# day01 第一部分  回顾以及反射

* **junit  单元测试**
* **反射**
* **注解**

## junit单元测试

测试分类：黑盒测试、白盒测试

* 黑盒测试：不需要写代码，给输入值，看程序是否能够输出期望的值
* 白盒测试：需要写代码。需要关注程序具体的执行流程



* **junit单元测试 ： 白盒测试**

**使用步骤**

 * 1. 定义一个测试类（测试用例）

      建议：  *测试类名：被测试的类名Test      eg:  Demo01JunitTest

      ​              包名：xxx.xxx.xx.test          eg: com.lym.test

   2. 定义测试方法：可以独立运行

      **建议**

      * 方法名：test测试的方法名          testAdd()
      * 返回值： void
      * 参数列表： 空参

   3. 给方法名上加上@Test

   4. 导入junit的依赖环境

      判定结果  红色，代表失败    绿色，代表成功 

      一般会使用断言操作来处理结果

      **断言操作**    Assert.assertEquals(期望值，真实值);

@Before 注解，一般用于一些资源的申请   在所有测试方法执行之前都会自动执行

@After注解， 一般用于一些资源的释放     在所有测试方法执行之后都会自动执行



**java代码在程序的三个阶段**

![image-20210917200117272](C:\Users\19900\AppData\Roaming\Typora\typora-user-images\image-20210917200117272.png)

# **反射**【框架设计的灵魂】

**反射的概念**：将类的各个组成部分封装为其他对象，这就是反射机制

* **好处**
* * 1.可以在程序运行过程中，操作这些对象
  * 2.可以解耦，提高程序的可扩展性



* 获取class对象的方式

**第一阶段：**Class.forName("全类名")     将字节码文件，加载进内存，返回Class对象

​			* 多用于配置文件，将类名定义在配置文件中。读取文件，加载类

**第二阶段：**类名.class   通过类名的属性class获取

​			* 多用于参数的传递

**第三阶段：**对象.getClass()方法  在Object类中定义

​			* 多用于对象的获取字节码的方式

```java
//抛出异常
Class cls1 = Class.forName("com.lym.domain.Person");
System.out.println(cls1);
Class<Person> cls2 = Person.class;
System.out.println(cls2);
Person person = new Person();
Class cls3 = person.getClass();
System.out.println(cls3);
```

```java
System.out.println(cls1==cls2);//true
System.out.println(cls1==cls3);//true
```

**==同一个字节码文件(*.class)，在内存中只会被加载一次==**,不论通过哪一种方式，获取的class对象都是同一个



* **Class对象的功能**
  
  * 获取成员变量们  
    * `Field[]  getFileds()`       获取**所有public**修饰的成员变量
    * `Field[]  getFiled(String name) `      获取指定名称的public修饰的成员变量
    * ​          
    * `Field[]  getDeclaredFileds()`       获取**所有的成员变量，**不考虑修饰符
    * `Field[]  getDeclaredFiled(String name)`   获取指定的成员变量，不考虑修饰符
    
    ==**注意：**访问私有权限成员变量时候，需要忽略访问权限 修饰符的安全检查，否则就会报出异常==
    
    `Field[]  getDeclaredFileds()`   所返回的==方法名.setAccessible(true);//暴力反射==
    
  * 获取构造方法们
    * `Constructor<?>[]   getConstructors()`
    * `Constructor<?>[]   getConstructor(类<?> .....parameterTypes)`
    *  
    * `Constructor<?>[]   getDeclaredConstructors()`
    * `Constructor<?>[]   getDeclaredConstructor(类<?> .....parameterTypes)`
    
    **构造方法是用来创建对象的**      构造器.newInstance();
    
    **注意：**如果使用空参数的构造器来创建对象，可以使用简化操作： class对象名.newInstace();
    
  * 获取成员方法们
    * `Method[]  getMethods()`
    * `Method[]  getMethod(String  name,类<?> .....parameterTypes)`
    *  
    * `Method[]  getDeclaredMethods()`
    * `Method[]  getDeclaredMethod(String  name,类<?> .....parameterTypes)`
    
    **方法执行：**    获取的方法名.invoke(对象名)；
    
  * 获取类名
    * String getName()



**案例：**

* 需求：写一个“框架”类，不能改变该类的任何代码的前提下，可以创建任意类的对象，可以执行任意方法
* **实现：**
  * 1.配置文件
  * 2.反射
* **步骤：**
  * 1.将需要创建的对象的全类名和需要执行的方法定义在配置文件中
  * 2.在程序中加载读取配置文件
  * 3.使用反射技术来加载类文件进内存
  * 4.创建对象
  * 5.执行方法

