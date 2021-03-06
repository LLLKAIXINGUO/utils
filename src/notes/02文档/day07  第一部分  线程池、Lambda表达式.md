# day07  第一部分  线程池、Lambda表达式

* 线程池
* Lambda表达式 ：可以用来简化之前学的匿名内部类的书写

**合理利用线程池的能够带来的三个好处：**

1. 降低资源消耗。减少了创建和销毁线程的次数，每个工作线程都可以被重复利用，可执行多个任务。
2. 提高响应速度。当任务到达时，任务可以不需要等到线程创建就能立即执行
3. 提高线程的可管理性。可以根据系统的承受能力，调整线程池中工作线程的数目，防止因为消耗过多的内存，而把服务器累趴下（每个线程需要大约1Mb内存，线程开的越多，消耗的内存也就越大，最后死机）

**线程池的使用： **jdk1.5之后提供的 

java.util.concurrent.Executors;  线程池的工厂类，用来生产线程池

   static  Executors   newFixedThreadPool(int nThreads)创建一个可重用固定线程数的线程池

参数：   int nThreads：创建线程池中包含的线程数量

返回值：ExecutorService接口，返回的是ExecutorService接口的实现类对象，我们可以使用ExecutorService接口接收（面向接口编程）

​    submit(Runnable  task)提交一个Runnable  任务用于执行

​	关闭/销毁线程池的方法      void   shutdown（）

**线程池的使用步骤：**

1. 使用线程池的工厂类Executors里边提供的静态方法newFixedThreadPool生产一个指定线程数量的线程池
2. 创建一个类，实现Runnable接口，重写run方法，设置线程任务
3. 调用ExecutorService中的方法submit，传递线程任务（实现类），开启线程 执行run方法
4. 调用ExecutorService中的方法 shutdown销毁线程池（不建议执行）





**Lambda表达式 **  【jdk1.8的新特性】     函数式编程思想

* 函数式编程思想概述【对比】
* * 面向对象思想：做一件事，找一个能解决这个事情的对象，调用对象的方法，完成事情
  * 函数式编程思想：只要能获取到结果就可以，谁去做的怎么做的都不重要，只要结果，不管实现过程

**使用Lambda省略格式**    Lambda表达式：是可推导，可省略的

凡是根据上下文推导出来的内容，都可以省略不写

可以省略的内容：

1. （参数列表）：括号中参数列表的数据类型，可以省略不写
2. （参数列表）：括号中的参数如果只有一个，那么类型和（）都可以省略
3. （一些代码）：如果（）中的代码只有一行，无论是否有返回值，都可以省略（{}，return,分号）  注意：要省略{}，return,分号必须一起省略



**Lambda的使用前提**====

1. 使用Lambda必须具有接口，且要求接口中**有且仅有一个抽象方法**

   ​		无论是jdk内置的Runnable、Comparator接口还是自定义的接口，只有当接口中的抽象方法存在且唯一时，才可以使用Lambda

2. 使用Lambda必须具有**上下文判断**

   ​		也就是方法的参数或局部变量必须为Lambda对应的接口类型，才能使用Lambda作为该接口的实例。



**备注：==有且仅有一个抽象方法的接口==，称为”函数式接口“**