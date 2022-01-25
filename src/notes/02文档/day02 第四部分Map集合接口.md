# day02 第四部分Map集合接口

Map集合是双链表

java.util.Map<k,v>集合

**Map集合的特点：**

1. Map集合是一个双列集合，一个元素包含两个值（一个key,一个value）
2. Map集合中的元素，key和value的数据烈性可以相同，也可以不同
3. Map集合中的key是不允许重复的，value可以重复
4. 一个key对应一个value值



### Map集合常用的接口

java.util.HashMap<k,v>集合  implements  Map<k,v>接口

**HashMap集合的特点：**

1. HashMap集合底层是==哈希表==：查询的速度特别快

   JDK1.8之前：数组+单向链表

   JDK1.8之后：数组+单向链表/红黑树{链表的长度超过八变成红黑树}，提高查询速度

2. hashMap集合是一个无序的集合，存储元素和取出元素的顺序有可能不一致



#### **LinkedHashMap集合的特点：**     extends   HashMap<k,v>集合

1. LinkedHashMap集合底层是哈希表+链表（保证迭代的顺序）
2. LinkedHashMap集合是一个有序 的集合，存储元素和取出元素的顺序是一致的





**Map集合的常用方法**

* public  V  put(K key,V  value);     把指定的键与指定的值添加到Map集合中

* * 返回值 ：  V
    *  存储键值对的时候，key不重复，返回值v的值为null
    * 存储键值对的时候，key重复，会使用新的value替换map中重复的value，返回被替换的value值
* public V remove(Object key);  把指定的键  所对应的键值对元素  在Map集合中删除，返回被删除元素的值。      
* * 返回值     V
  * * key存在，v返回被删除的值
    * key不存在，v返回null
* public boolean containsKey(Object key); 判断集合中是否包含这个Key   若包含，返回true, 若不包含，返回false

 

#### **遍历map集合**

keySet()       :     第一种方式把map集合所有的key取出来存储到Set集合中

EntrySet()       第二种方式  

```java

* Map集合的第一种遍历方式：通过键找值的方式
* Map集合的方法：
*       Set<K> keySet()  返回此映射中包含的键的Set视图
*   实现步骤：
*      1.使用Map 集合中的方法keySet（），把Map集合中所有的key 取出来，存储到一个set集合中
*      2.遍历set集合，获取Map集合中的每一个key
*      3.通过Map集合中的方法get(key),通过key找到value
```



```java
Map<String,Integer> map = new HashMap<>();
map.put("王二",12);
map.put("张三",15);
map.put("李四",18);
Set<String> keys = map.keySet();
Iterator<String> iterator = keys.iterator();
while (iterator.hasNext()){
    String key = iterator.next();
    Integer value = map.get(key);
    System.out.println(key+"="+value);
}
```

Map.Entry<K,V>;在Map接口中有一个内部的接口Entry   遍历Map集合

作用：当Map集合一创建，那么就会在Map集合中创建一个Entry对象，用来记录键与值（键值对对象，键与值的映射关系）

**实现步骤：**

1. 使用Map集合中的方法entrySet(),把Map集合中多个Entry对象取出来，存储到一个Set集合中
2. 遍历Set集合，获取每一个Entry对象
3. 使用Entry对象中的方法  getKey()  和  getValue()获取键值对

```java
Map<String,Integer> map = new HashMap<>();
map.put("杨c",21);
map.put("杨a",15);
map.put("杨b",18);
Set<Map.Entry<String, Integer>> sets = map.entrySet();
//遍历set集合，获取每一个Entry对象
Iterator<Map.Entry<String, Integer>> iterator = sets.iterator();           //将EntrySet对象迭代
while(iterator.hasNext()){
    Map.Entry<String, Integer> entry = iterator.next();
    String key = entry.getKey();
    Integer value = entry.getValue();
    System.out.println(key+"="+value);
}
```



**HashMap存储自定义类型键值**

**Map集合保证key是唯一的**

​			**作为key的元素，必须重写hashCode方法和equals方法，以保证key唯一**



HashMap存储自定义类型键值

  key:String 类型

​             String 类重写hashCode方法和equals方法，可以保证key唯一

value:Person类型

​				value可以重复（同名同年龄的人视为同一个人）





**LinkedHashMap<k,v>  extends  HashMap<k,v>**

Map接口的哈希表和链接列表实现，具有可预知的迭代顺序

**底层原理：**

​			哈希表+链表（记录元素的顺序）





**HashTable<k,v>**   

**HashTable:**底层是一个哈希表，是一个线程安全的集合，是单线程的集合，速度慢

**HashMap:**都测不过是一个哈希表，是一个线程不安全的集合，是多线程的集合，速度快





**Map集合的练习：计算要给字符串中每个字符出现的次数**

* 使用scanner获取一个字符串

* 遍历字符串中的每一个字符
  * String类的方法toCharArray,把字符串转换为一个字符数组，遍历数组	String类的方法length()+ charAt(索引)
* 创建map集合，使用获取到的字符，去Map集合判断Key是否存在
* 遍历输出即可

```java
Scanner sc = new Scanner(System.in);
System.out.println("请输入一个字符");
String str = sc.next();
Map<Character,Integer> map = new HashMap<>();
for (char c : str.toCharArray()) {
    if(map.containsKey(c)){ //key存在
        Integer value = map.get(c);
        value++;
        map.put(c,value);
    }else{     //key不存在
        map.put(c,1);
    }
}
for (Character key : map.keySet()) {
    Integer value = map.get(key);
    System.out.println(key+"="+value);
}
```





## **jdk9对集合添加的优化 ** jdk9的新特性

list接口、Set接口、Map接口：里面增加了一个静态的方法of，可以给集合一次性添加多个元素

**使用前提：**

* 当集合中存储的元素的个数已经确定了，不再改变时使用

**注意：**

1. of方法只适用于list接口、Set接口、Map接口，不适用于接口的实现类
2. of方法的返回值是一个不能改变的集合，集合不能再使用**add,put**方法添加元素，会抛出异常
3. Set接口和Map接口在调用of方法的时候，不能有重复元素，否则会抛出异常





**Debug追踪**    Debug调试程序：可以让代码逐行执行，查看代码执行的过程，调试程序中出现的bug           使用断点

**使用方式：**

* *  在行号的右边鼠标左键单击，添加断点（哪里有bug添加到哪里）
  * 程序就会停留在添加的第一个断点
  * **执行程序：**
    * f8逐行执行
    * f7进入方法中
    * shift+f8跳出方法
    * f9跳到下一个断点 ，如果没有下一个，就结束程序
    * ctrl+f2：退出debug模式，停止程序



**斗地主综合案例：** ：实现按照顺序排列 

1.准备牌
2.洗牌
3.发牌
4.排序
5.看牌

