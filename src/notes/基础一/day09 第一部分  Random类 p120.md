# day09 第一部分 Random类 p120

Random r = new Random()；

int   i=r.nextInt(参数)； eg:10   [0,10)         参数是左闭右开区间 



```java
Person[] array=new Person[3];//数组对象
```



```java
Person[] array=new Person[3];
        Person one = new Person("小A",12);
        Person two = new Person("小B",22);
        Person three = new Person("小C",32);
        array[0]=one;//将one的地址值  赋值给了array[0]
        array[1]=two;
        array[2]=three;
//        System.out.println(array[0]);
      System.out.println(array[0]);//com.lym.demo01.Person@75412c2f
        System.out.println(array[0].getName()+"  "+array[0].getAge());
```

* 数组的缺点  ：程序一旦运行，数组的长度不能发生改变

  为了解决这一问题，使用集合类   ArrayList       day07_13



1. 对于ArrayList来说，有一个<E>代表泛型
2. 泛型：也就是装在集合当中的所有元素类型，全都是统一的什么类型

* ==**注意**：泛型只能是引用类型，不能是基本类型==

  ```java
  ArrayList<数据类型> arrayList = new ArrayList<>();//只能是引用数据类型，不能使用基本数据类型
   System.out.println(arrayList);//如果不进行添加，直接打印输出的是[]
  ```
  
  

对于ArrayList直接打印出来的不是地址值  如果为空是一个[]

* 向集合中添加元素，使用add方法。

**ArrayList当中的常用方法整理**

* public boolean add(E e);向集合中添加元素，参数的类型和泛型一致

**注意：ArrayList集合的ADD方法向集合中添加元素一定是成功的，因此可用返回值可不用，对于其他集合不一定可以添加成功的。**

* public E get(int index);从集合当中获取元素，参数是索引编号，返回值是对应元素
* public E remove(int index);从集合中删除元素，参数是索引编号，返回值就是被删除的元素
* public int size();获取集合的尺寸长度，返回值是集合中包含的元素个数

