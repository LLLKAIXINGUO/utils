# day09 第三部分  static关键字

### static关键字

如果一个成员变量使用了static关键字，那么这个变量不再属于对象自己，而是属于所在的类。多个对象共享同一份数据

eg:使用static关键字修饰成员变量

```java
public class Student {
    private String name;
    private int age;
    static String room;
    private int id;
    private static int idcount = 0;//自动累加

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.id = ++idcount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student() {
        this.id = ++idcount;
    }

    public static String getRoom() {
        return room;
    }

    public static void setRoom(String room) {
        Student.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
```

```java
Student one = new Student("小二",20);

Student two = new Student("小三",30);
one.room="101教室";
System.out.println("名字是："+one.getName()+"年龄是："+one.getAge()
        +"教室是："+one.room+"学号是"+one.getId());
System.out.println("名字是："+two.getName()+"年龄是："+two.getAge()
        +"教室是："+two.room+"学号是"+two.getId());
Student three = new Student("小四",40);
System.out.println("学号是："+three.getId());
```

### 使用static关键字修饰成员方法

```java
public static void staticMethod(){

​	System.out.println("这是一个静态方法");

}
```

静态方法可以直接使用  类名称.静态方法名来调用    （推荐）

**根据类名称访问静态成员变量的时候，全程和对象没关系，只和类有关系。**

不推荐使用创建对象后   使用  对象名.方法名来调用

**对于本类当中的静态方法，可以省略类名称**

**静态方法可以访问静态变量，不能直接访问非静态变量**

原因： 内存中【先】有的静态内容，【后】有的非静态内容

**静态方法中不能使用this关键字**

原因：this代表当前对象，通过谁调用的方法，谁就是当前对象   静态方法使用的是      类名.静态方法名来调用的



* 静态代码块的==格式==是：
* 特点：当第一次用到本类时，静态代码块执行唯一的==一次==。
* 静态内容总是优先于非静态
* **静态代码块的典型用途**：用来一次性的对静态成员变量进行赋值

```java
public class 类名称{

​		static{

​				//静态代码块的内容

}

}
```

