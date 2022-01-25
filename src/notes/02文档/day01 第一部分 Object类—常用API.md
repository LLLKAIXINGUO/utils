# day01  第一部分 Object类—常用API

### Object类

在lang包下，所以不用导包

java.==lang==.Object  类是Java语言中的所有的类的父类

toString()方法--------》Object类

创建对象后，直接使用对象.toString方法，出现是它的地址值。

![image-20210908163853428](C:\Users\19900\AppData\Roaming\Typora\typora-user-images\image-20210908163853428.png)

也可以使用快捷键，对toString方法进行重写

//看一个类是否重写了toString 方法，直接打印这个类的对象即可，如果没有重写toString方法，那么打印的是地址值。



equals()方法--------》Object类

用来比较对象是否相等

```java
public boolean equals(Object obj) {
    return (this == obj);
}//Object obj:可以传递任意的对象
```

重写equals方法

**多态是有弊端的：**无法使用子类特有的方法和属性。

**解决：**向下转型

```java
//重写equals方法   equals本身是比较两个对象的地址值，重写后比较对象的属性
//equals重写
@Override
public boolean equals(Object o) {
    if (this == o) return true;//和本身相比较
    //getClass() != o.getClass()使用反射技术，比较参数o是否是所使用的类的类型的    等效与obj instances person
    if (o == null || getClass() != o.getClass()) return false;
    PersonToString person = (PersonToString) o;
    return age == person.age &&
            Objects.equals(name, person.name);
}
```

**使用Object类的equals方法 不能防止空指针异常，使用Objects类的equals方法，可以有效的防止程序出现空指针异常错误**