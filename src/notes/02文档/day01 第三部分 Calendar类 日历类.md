# day01 第三部分 Calendar类 日历类

## 日历类是在date类之后出来的，被Calendar替代了许多

**Calendar【抽象类】**

提供了很多操作日历字段的方法

**是抽象类**，无法直接创建对象使用 ，里面有一个**静态方法**getInstace(),返回了Calender类的子类对象

static  Calendar  getInstance() 使用默认时区和语言环境获得一个日历

```java
//用一个父类接受一个子类对象   多态
Calendar instance = Calendar.getInstance();//静态方法使用类名. 调用
System.out.println(instance);
```

Calendar**常用的成员方法：**

public int get(int field);返回给定日历字段的值



```java
Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH);
        System.out.println(year);//2021
        System.out.println(month);//8 需要加1，返回的是西方的月份（0-11）
```

** 参数：传递指定的日历字段（YEAR,MONTH.....）

**  返回值：日历字段代表的具体的值



public int set(int field，int value);将给定的日历字段设置为给定值

public abstract void add(int field,int amount);根据日历的规则，为给定的日历字段添加或减少指定的时间量

public Date getTime();返回一个表示此calendar时间值（从历元到现在的毫秒偏移量）的Date对象

```java
Calendar c = Calendar.getInstance();
Date time = c.getTime();
System.out.println(time);
```

**成员方法的参数：**

**int field: 日历类的字段，可以使用Calendar类的静态成员变量获取**



