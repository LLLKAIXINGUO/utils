# day01 第四部分 System类

public static Long currentTimeMillis();返回当前系统的时间，单位是毫秒值

#### currentTimeMillis方法:获取当前时间的毫秒值

**public static void arraycopy(Object src,int srcPos,Object dest,int destPos,int length);**         将数组中指定的数据拷贝到另一个数组中

**参数：**  

* src------------------源数组
* srcPos-------------源数组中的起始位置
* dest----------------目标数组
* destPos-----------目标数组中的指定位置
* length--------------要复制的数组元素的数量



练习：将src数组中的前三个元素复制到dest数组的前3个位置上，

复制元素前：src数组元素[1,2,3,4,5],dest数组元素[6,7,8,9,10]

复制元素后：src数组元素[1,2,3,4,5],dest数组元素[1,2，3，9,10]

```java
//注意，数组的长度一旦程序运行就不可以发生改变
int[] src={1,2,3,4,5};
int[] dest={6,7,8,9,10};
System.out.println(Arrays.toString(dest));//复制前[6, 7, 8, 9, 10]
System.arraycopy(src,0,dest,0,3);
System.out.println(Arrays.toString(dest));//复制后[1, 2, 3, 9, 10]
```



### StringBuilder   字符串缓冲区[可以提高字符串的操作效率（看成一个长度可以变化的字符串]

* 初始容量为16个字符，一旦超过16就会自动扩容

StringBuilder底层也是一个数组，但是没有被final修饰，可以改变长度

String字符串的底层是一个被final修饰的字节数组

private  final  byte[] value;

字符串是常量，它们的值在创建之后不能改变   **StringBuilder 可以提高字符串的效率**

eg : String s= "a"+"b"+"c" ="abc";

在内存中就会有多个字符串，占用空间多，效率底下

* “a” , "b" , "c"     三个字符串
* "a"+"b"      "ab"  一个字符串
*  "ab" + “c”     "abc"            一个 字符串

**构造方法**

**StringBuilder str = new StringBuilder();空参构造方法**

**StringBuilder str = new StringBuilder(“abc”);带字符串构造方法**

**成员方法**

public StringBuilder  append(....);添加任意类型数据的字符串形式，并返回当前对象自身       **链式编程**     

```java
  StringBuilder str = new StringBuilder();
        str.append("dfsdafd");
        StringBuilder str1 = str.append(123);
        System.out.println(str);//dfsdafd123
        System.out.println(str1);
        System.out.println(str==str1);//比较的是地址  true
```

 public String toString();        StringBuilder和String可以相互转换

* String---->StringBuilder:可以使用StringBuilder的构造方法
* StringBuilder---->String:可以使用StringBuilder中的toString方法

 public String toString();  将当前StringBuilder对象转换为String对象

 



### 包装类： jdk1.5以后  自动拆箱自动装箱

### **基本数据类型，使用起来非常方便，但是没有对应的方法来操作这些基本类型的数据，可以使用一个类把基本类型的数据装起来，从类中定义一些方法，这个类叫做包装类，我们可以使用类中的方法来操作这些基本类型的数据**

装箱与拆箱：  把基本类型数据变成包装类        把包装类 变成基本数据类型



**基本类型与字符串之间的相互转换**

基本类型----------》字符串（String）

1. 基本类型的值+“”      最简单的方法 {工作中常用的方法}
2. 包装类的静态方法同String（参数），不是Object类的toString()重载          static  String  toString(int i)      返回一个表示指定整数的String  对象
3. String类的静态方法   static  String  valueOf(int i) 返回int参数的字符串表示形式



字符串（String）----->基本类型

* 使用包装类的静态方法 parseXXX("数值类型的字符串");

* * Integer类：  static int  parseInt(String s)
  * Double类：   static  double parseDouble(String s)

  
