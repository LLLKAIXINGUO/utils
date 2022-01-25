# day06 第一部分    线程、同步

#### **多线程的运行的原理**



**Thread类的常用方法**

获取线程的名称：

​        	1.使用Thread类中的方法getName()

​						String   getName() 返回该线程的名称

​			2.可以先获取到当前正在执行的线程，使用线程中的方法getName()获取线程的名称

​						static  Thread  currentThread() 返回当前正在执行的线程对象的引用。

```java
public class MyThread extends Thread {
    @Override
    public void run() {
//        获取线程名称第一种方法
//        String name = getName();
//        System.out.println(name);
        //        获取线程名称第二种方法
        Thread thread = currentThread();
        System.out.println(thread);//thread.getName()
    }
}
```

```java
MyThread thread = new MyThread();
thread.start();
MyThread thread1 = new MyThread();
thread1.start();
MyThread thread2 = new MyThread();
thread2.start();
```

设置线程名称的方法（了解即可）

1. 使用Thread类中的方法setName(名字)

   void  setName(String name)改变线程名称，使之与参数name相同

2. 创建一个带参数的构造方法，参数传递线程的名称，调用父类的带参构造方法，把线程名称传递给父类，让父类（Thread）给子线程起一个名字



public  static  void  sleep(long millis); 使当前正在执行的线程以指定毫秒数暂停，（暂时停止运行）

```java
for (int i = 0; i < 100; i++) {
    System.out.println(i);
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}//使其每隔一秒执行一次
```

 



**创建多线程的第二种方式**

实现Runnable接口的类，该类然后实现run方法。然后可以分配该类的实例

**实现Runnable接口创建多线程的好处**

1. 避免了单继承的局限性

   一个类只能继承一个类，类继承了Thread类，就不能继承其他的类

   实现了Runnable接口，还可以继承其他的类，实现其他接口

2. 增强了程序的扩展性，降低了程序的耦合性（解耦）

   实现Runnable   接口的方式，把设置线程任务和开启新线程进行了分离（解耦）

   实现类中，重写了run方法，用来设置线程任务

   创建Thread类对象，调用start方法，开启新的线程

**匿名内部类的方式来实现线程的创建**

**匿名内部类的作用**： 简化代码

​				把子类继承父类，重写父类的方法，创建子类对象合成一步完成

​				把实现类实现类接口，重写接口中的方法，创建实现类对象合一步完成

**格式：**

new   父类/接口（）{

​      重复父类/接口中的方法

}；

**线程安全问题：** 多线程访问了共享的数据  或 多线程访问了 不存在的数据，都会出现线程安全问题

线程安全问题是不能产生的，我们可以让一个线程在访问共享数据的时候，无论是否失去了cpu的执行权，让其他线程只能等待，等待当前线程卖完票，然后其他线程再执行

**解决线程安全问题**

1. **同步代码块**
2. **同步方法**
3. **锁机制**

* 同步代码块：synchronlized关键字可以用于方法中的某个区块中共，表示只对这个区块的资源实行互斥访问。

  **格式：**     synchronized(同步锁){

  ​							需要同步操作的代码

  ​                                    }

**注意：**

1. 通过代码块中的锁对象，可以使用任意的对象

2. 但是必须保证多个线程使用的锁对象是同一个

3. 锁对象作用

   ​	把同步代码块锁住，只让一个线程再同步代码块中执行



* 同步方法 使用步骤

1. 把访问了共享数据的代码抽取出来，放到一个方法中
2. 在方法上添加synchronized修饰符

**格式：**定义方法的格式

修饰符    synchronzized   返回值类型    方法名（参数列表）{

​          需要同步操作的代码

}

* 使用lock锁：     lock接口中的方法
  * void  lock() 获取锁
  * void  unlock()  释放锁

**使用步骤：**  

1. 在成员位置创建一个Reentrantlock对象
2. 在可能会出现安全问题的代码前调用lock接口中的方法lock获取锁
3. 在可能出现安全问题的代码后调用lock接口中的方法unlock释放锁
