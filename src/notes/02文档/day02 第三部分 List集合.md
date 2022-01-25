# day02 第三部分 List集合接口

list**集合三大特点**          list接口，extends Collection接口

* 有序        存储元素和取出元素的顺序是一致的
* 有索引
* 允许重复

 List接口中带索引的方法（特有）

* public void add(int index, E element);将指定的元素，添加到该集合中的指定位置上
* public E get(int index);返回集合中指定位置的元素
* public E remove(int index)；移除列表中指定位置的元素，返回的是被移除的元素
* public E set(int index, E element);用指定元素替换集合中指定位置的元素，返回值是更新前的元素
* * **注意：**操作索引的时候，一定要防止索引越界异常



List集合的重要的实现类： ArrayList 【多线程】     LinkedList【多线程】

LinkedList 是list集合的链表实现：有大量的针对 首尾元素的操作

使用LinkedList集合，不能使用多态的

* public  void   addFirst(E e);将指定元素插入到此列表的开头
* public  void   addLast(E e);将指定元素插入到此列表的结尾  等效于add方法
* public E getFirst();返回此列表的第一个元素
* public E getLast();返回此列表的最后一个元素
* public E removeFirst();删除并返回此列表的第一个元素
* public E removeLast();删除并返回此列表的最后一个元素
* public E pop();从此列表所表示的堆栈处弹出一个元素
* public void push(E e);将元素推入到此列表所表示的堆栈
* public Boolean isEmpty();如果列表不包含元素，则返回true

public E addFirst();   等效于  public void push(E e);

  public E removeFirst();       等效于  public E pop();



Vector 接口    extends      List    【单线程的】  了解即可

   



# set接口

**特点：**

* 不允许存储重复的元素
* 没有索引，没有带索引的方法，也不能使用普通的for循环遍历

### Hashset特点

1. 不允许存储重复的元素
2. 没有索引，没有带索引的方法，也不能使用普通的for循环遍历
3. 是一个**无序**的集合，存储元素和取出元素的顺序有可能不一致
4. 底层是一个哈希表结构（查询的速度非常的快）



### 哈希值：

### 是一个十进制的整数，由系统随机给出（就是对象的地址值，是一个逻辑地址，是模拟出来的地址，不是数据实际存储的物理地址）

在Object类中有一个  hashcode()方法，可以获取对象的哈希值

**int hashCode()**  返回该对象的哈希码值

hashcode 方法的源码：

​	

```java
	public  native  int  hashCode();

​	native:代表该方法调用的是本地操作系统的方法
```

**HashSet集合存储数据的结构：（哈希表）结构**

jdk1.8之前：哈希表=数组+链表

jdk1.8之后：

* 哈希表=数组+链表

* 哈希表=数组+红黑树（提高查询的速度）

  ==如果链表的长度超过了8位，那么就会把链表转换为红黑树（提高查询速度）==

**哈希表的特点：**速度快

==数组结构：把元素进行了分组（相同哈希值的元素是一组）==

==链表/红黑树结构：把相同哈希值的元素连接到一起==

存储数据到集合中，先计算元素的哈希值

**两个元素不同，但是哈希值相同------->哈希冲突**



//set集合不允许存储重复元素的原理

**set集合存储元素唯一：**

存储的元素（String,Integer,.........Student,Person...）,必须重写hashCode()方法和equals方法。



## LinkedHashSet集合     extends  HashSet集合

**特点：**  底层是一个哈希表（数组+链表/红黑树）+链表，多了一条链表（记录元素的存储顺序），保证元素有序



## 可变参数：jdk1.5以后出现的新特性

#### 使用前提：

​			**当方法的参数列表数据类型已经确定，但是参数的个数不确定，就可以使用可变参数**。

### **使用格式：**定义方法时使用

 修饰符   返回值类型    方法名（数据类型...变量名）{}

### 可变参数的原理：

​			可变参数底层就是一个数组，根据传递参数个数不同，会创建不同长度的数组，来存储这些参数传递的参数个数，可以是0个（不传递），1，2...多个

```java
public static void main(String[] args) {
    int add = add(3, 4, 5);
    System.out.println(add);
}
public static int add(int...arr){
    int sum=0;
    for(int a:arr){
        sum+=a;
    }
    return sum;
}         //可变参数
```

**==可变参数的注意事项：==**

1. 一个方法的参数列表，**只能有一个**可变参数

   public  static void method(int...a,String...b);错误，因为只能有一个

2. 如果方法的参数有多个，那么可变参数必须写在参数列表的末尾

​                 即public  static void method(int...a,String name);错误

​					public  static void method(String name，int...a);正确

**可变参数的特殊（终极）写法：**public  static void method(Object...obj);





**操作集合的Collections**     是集合工具类，用来对集合进行操作

**操作集合的Collections** 的三个常用方法

* public  static  <T>  boolean  addAll(Collection<T>  c, T...elements):往集合中添加一些元素
  * eg:  Collections.addAll(list,"a","b","c","d");
* public  static  void  shuffle(List<?> list)打乱顺序：打乱集合顺序
* public static <T> void sort(List<T> list);将集合中元素按照默认的规则排序【升序】
* public static <T> void sort(List<T> list,Comparator<? super T>);将集合中元素按照指定规则排序

**注意：**被排序的集合里边存储的元素，必须实现Comparable,重写接口中的方法

![image-20210913124414734](C:\Users\19900\AppData\Roaming\Typora\typora-user-images\image-20210913124414734.png)

![image-20210913124736220](C:\Users\19900\AppData\Roaming\Typora\typora-user-images\image-20210913124736220.png)

