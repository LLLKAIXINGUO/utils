# day02 第一部分 Collection、泛型

**Collection集合**  

* **集合**  ： 集合是java中提供的一种容器，可以用来存储多个数据

集合和数组既然都是容器，它们之间有什么区别么？？？

* 数组的长度是固定的。集合的长度是可变的
* 数组中存储的是统一类型的元素，可以存储基本数据类型值、存储对象。集合存储的都是对象。而且对象的类型可以不一致，在开发中一般当对象多的时候，使用集合进行存储。

### 集合框架

**集合框架的学习方式：**

1. 学习顶层学习顶层接口/抽象类中共性的方法，所有的子类都可以使用
2. 使用底层：底层不是接口就是抽象类，无法创建对象使用，需要使用底层的子类创建对象使用

![image-20210912145424002](C:\Users\19900\AppData\Roaming\Typora\typora-user-images\image-20210912145424002.png)

**学习集合的目标：**

1. 会使用集合存储数据
2. 会遍历集合，把数据取出来
3. 掌握每种集合的特性

**Collection集合**  ：定义的是所有单列集合中共性的方法，所有的单列集合都可以使用共性的方法  【没有带索引的方法】



**List接口：**

1. 有序的集合（存储和取出的顺序相同）
2. 允许存储重复的元素
3. 有索引，可以使用普通的for循环遍历

**Set接口**

1. 不允许存储重复元素
2. 没有索引（不能使用普通的for循环遍历）
3. TreeSet集合和HashSet集合是无序的集合，【存储取出元素的顺序有可能不一致】     LinkedHashSet集合有序的集合，存和取的顺序是一样的

 **Collecetion  常用功能：**

java.util.Collection接口

​		所有单列集合的最顶层的接口，里边定义了所有单列集合共性的方法

​		任意的单列集合都可以使用Collection接口中的方法

**共性的方法：**

* public  boolean  add(E e);   把给定的对象添加到当前集合中
* public void  clear();      清空集合中的所有元素
* public boolean  remove(E e); 把给定的对象在当前集合中删除
* public boolean contains(E e);判断当前集合中是否包含给定的对象
* public boolean isEmpty(); 判断当前集合是否为空
* public int size();返回集合中元素的个数
* public Object[]  toArray(); 把集合中的元素，存储到数组中

```java
//使用多态创建集合
Collection<String> collection = new ArrayList<>();
System.out.println(collection);//[]
collection.add("一");
collection.add("二");
collection.add("小例子");
System.out.println(collection);//[一, 二, 小例子]
collection.remove("二");
System.out.println(collection);//[一,  小例子]
Object[] objects = collection.toArray(); //把集合中的元素存储到数组中
for (int i = 0; i < objects.length; i++) {
    System.out.println(objects[i]);
}
```



**Iterator 接口**             **Iterator 迭代器**是一个接口

   **Iterator 迭代器**是一个接口，我们无法直接使用，需要使用Iterator接口的实现类对象，获取实现类的方法比较特殊 

Collection 接口中有一个方法，叫iterator(),这个方法返回的就是迭代器的实现类对象

* **迭代：**Collection集合中通用的取出元素的方式

  在取元素之前，先要判断集合中有没有元素，如果有，就把这个元素取出来，继续再判断，如果还有就再取出来。一直把集合中的元素全部取出。这种取出的方式专业术语成为迭代

**Iterator 接口的两个常用的方法：**

boolean hadNext()  如果仍有元素可以迭代，则返回true

​        判断集合中还有没有**下一个**元素，有就返回true,没有就返回false

E   next()    返回迭代的下一个元素

​         取出集合中的下一个元素

 **迭代器的使用步骤（重点）：**

1. 使用集合中的方法iterator() 获取迭代器的实现类对象，使用Iterator接口接收（多态）
2. 使用Iterator接口中的方法hashNext盘算还有没有下一个元素
3. 使用Iterator接口中的方法next取出集合中的下一个元素

```java
Collection<String> coll = new ArrayList<>();
coll.add("a");
coll.add("b");
coll.add("c");
coll.add("d");
coll.add("e");
Iterator<String> iterator = coll.iterator();
boolean b = iterator.hasNext();
String next = iterator.next();
System.out.println(next);//a

boolean b = iterator.hasNext();
String next = iterator.next();
System.out.println(next);//b

boolean b = iterator.hasNext();
String next = iterator.next(); 
System.out.println(next);//c     优化方式：重复步骤，使用循环  未知循环几次，使用while循环 ，循环结束的条件 iterator.hasNext(); 是false


while(iterator.hasNext()){
    String next = iterator.next(); 
    System.out.println(next);
}
```

* **注意：**Iterator<E>接口也是有泛型的，迭代器的泛型跟着集合走，集合是什么泛型，迭代器就是什么泛型



**增强for循环**:用来遍历集合和数组

**格式：**

```
for(集合/数组的数据类型  变量名：集合名/数组名){

​	sout(变量名);

}

eg:    int[] arr={1,2,3,4};

​         for(int i: arr){

sout(i)

}
```

底层使用的也是迭代器，使用for循环的格式，简化了迭代器的书写       jdk1.5以后出现的新特性

所有的单列集合都可以使用



**泛型：E**是一种未知的数据类型，当我们不知道使用什么数据类型的时候，**可以使用泛型**

泛型也可以看成是一个变量，用来接收数据类型

创建集合对象的时候，就会确定泛型的数据类型

**创建集合对象，不使用泛型：**

* 好处： 集合不适用泛型，默认的类型就是Object类型，可以存储任意类型的数据        
* 弊端：不安全，会引发异常

**创建集合对象，使用泛型：**

* 好处：
* 1. 避免了类型转换的麻烦，存储的是什么类型，取出的就是什么类型
  2. 把运行期异常（代码运行时爆出的异常），提升到了编译期（写代码时爆出的异常）

**泛型的类定义和使用：**

```java
public class Student<E> {
    private E name;
    private E age;

    public E getName() {
        return name;
    }

    public void setName(E name) {
        this.name = name;
    }

    public E getAge() {
        return age;
    }

    public void setAge(E age) {
        this.age = age;
    }
}
```

```java
public static void main(String[] args) {
    Student<Integer> stu = new Student<>();//创建对象时，对象使用什么泛型，结果就是什么泛型
    Integer age = stu.getAge();
    stu.setName(1);
    Integer name = stu.getName();
    System.out.println(name);

    Student<String> stu1 = new Student<>();
    stu1.setName("xiaozha");
    String name1 = stu1.getName();
    System.out.println(name1);
}
```

**泛型的方法定义和使用：**泛型定义在方法的修饰符和返回值类型之间

**格式：**

```java
修饰符<泛型>   返回值类型   方法名（参数列表（使用泛型））{

​					方法体；

}//含有泛型的方法，在调用方法的时候确定泛型的数据类型  传递什么类型的参数，泛型就是什么类型
```

//定义一个含有泛型的方法

```java
public <M> void method(M m){

	sout(m);

}
```

```java
//创建测试类测试泛型的使用
public static void main(String[] args) {
    Demo02Fanxing fanxing = new Demo02Fanxing();
    fanxing.method(12);
    fanxing.method("fds");
    fanxing.method(true);
}
```

**定义含有泛型的接口：**





**注意：**泛型没有继承概念

**泛型通配符**：？ ：代表任意的数据类型

**使用方式：** 不能创建对象使用 ；  只能作为方法的参数使用



**泛型的上限限定：**       ？ extends E  代表使用的泛型只能是E类型的子类/本身

**泛型的下限限定：**       ？ super E  代表使用的泛型只能是E类型的父类/本身

   



**斗地主的综合案例**

1. 准备牌  54张，存储到一个集合中       特殊牌 ： 大 、 小王     其他52张牌：

   1. * 定义一个==数组/集合==，存储四种花色♠♥♦♣  
      * 定义一个==数组/集合==，存储13个序号：2、A、K......3

   循环嵌套遍历两个==数组/集合==，组装52张牌

2. 洗牌 ，

    使用工具类Collection的方法

   static  void   shuffle(List<?> list)  使用指定的随机源对指定列表进行置换，会随机的打乱集合中的元素的顺序

3. 发牌

   要求：  1. 一人17张牌，剩余三张作为底牌，一人一张轮流发牌

   定义4个集合，存储3个玩家的牌和底牌

   索引%3， 有三个值（0，1，2）

   索引>=51,改为底牌

4. 看牌   

   直接打印集合，遍历存储玩家和底牌的集合

```java
ArrayList<String> poker = new ArrayList<>();
poker.add("大王");
poker.add("小王");
String[] colors = new String[]{"❤","♠","♦","♣"};
String[] numbers=new String[]{"2","A","K","Q","J","10","9","8","7","6","5","4","3"};
for(String number:numbers){
    for(String color:colors){
        poker.add(color+number);
    }
}
//2.洗牌
Collections.shuffle(poker);
System.out.println(poker);

//3.发牌    首先创建四个集合存储玩家手牌和底牌 然后分发给四个集合中
ArrayList<String> play01 = new ArrayList<>();
ArrayList<String> play02 = new ArrayList<>();
ArrayList<String> play03 = new ArrayList<>();
ArrayList<String> dipai = new ArrayList<>();
for(int i=0;i<poker.size();i++){
    String p =poker.get(i);
    if(i>=51){
        dipai.add(p);
    }else if(i%3==0){
        play01.add(p);
    }else if(i%3==1){
        play02.add(p);
    }else if(i%3==2){
        play03.add(p);
    }
}
//4.发牌
System.out.println("刘德华1的牌："+play01);
System.out.println("刘德华2的牌："+play02);
System.out.println("刘德华3的牌："+play03);
System.out.println("底牌："+dipai);
```

