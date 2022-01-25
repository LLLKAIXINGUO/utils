# day01 第二部分 日期时间类

### Date类

java.util.Date:表示日期和时间的类

Date 表示特定的瞬间，精确到**毫秒**：千分之一秒

毫秒值的作用：可以对时间和日期进行计算

可以把日期转换为毫秒，然后再把毫秒转换为日期，就可以计算出 两个日期的时间间隔

时间原点：1970年 1 月 1 日00：00：00

Date类 是被toString方法冲重写的类    创建无参数的Date类，输出对象为

`Wed Sep 08 17:36:05 CST 2021` 格式

创建有参数的Date类，会把毫秒值【传入Long类型的 毫秒值】转换为日期  eg:

Date date =  new Date(0L);

`Thu Jan 01 08:00:00 CST 1970`



Long getTime()方法，返回自1970年1月1日00：00：00到此Date对象表示的毫秒值（相当于System.currentTiimeMillis()方法）



## DateFormat类（抽象类）无法直接使用，使用其子类  SimpleDateFormat类

是日期/时

象类，通过这个类可以完成日期和文本之间的格式转换，实现Date对象与String对象之间进行来回转换

* **格式化：** 按照指定的格式，从Date对象转换为String对象
* **解析：**     按照指定的格式，从String对象转换为Date对象

**成员方法：** 

* ==String format(Date date)==  按照指定的模式，把Date日期，格式化为符合模式的字符串
* ==Date parse(String source)== 把符合模式的字符串，解析为Date日期

**使用DateFormat类中的方法format（Date date），把日期格式化为文本：**步骤

1. 创建SimpleDateFormat对象，构造方法中传递指定的模式
2. 调用SimpleDateFormat对象中的方法format,按照构造方法中指定的模式，把Date日期格式化为符合模式的字符串（文本）

```java
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//"yyyy-MM-dd HH:mm:ss"日期格式，可变动
Date date = new Date();
String format = simpleDateFormat.format(date);
System.out.println(format);//2021-09-09 20:47:52
```

### 练习：使用时间/日期类，计算一个人从出生对今天一共是多少天 

```java
Scanner sc = new Scanner(System.in);
System.out.println("请输入您的出生日期,格式：yyyy-MM-dd：");
String birthday = sc.next();//获取日期字符串
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//定义字符串转换的格式
Date parse = sdf.parse(birthday);  //将字符串转为date类型的数据
long birthdayMill = parse.getTime();  //将date类对象转为毫秒值
Date date = new Date();           //这两步相当于
long today = date.getTime();      //System.currentmillions
long total = today-birthdayMill;
System.out.println("截至到今天，您一共活了"+total/60/24/1000/60+"天");    
```

![image-20210909215002162](C:\Users\19900\AppData\Roaming\Typora\typora-user-images\image-20210909215002162.png)2021-9-9