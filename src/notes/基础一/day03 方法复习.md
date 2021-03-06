# day03 方法复习 p63

## 方法的三种调用格式：

* 单独调用：             方法名（参数）；

* 打印调用：

  ```java
  System.out.println(方法名（参数）);
  ```

* 赋值调用：

  ```java
  数据类型  变量名称=方法名（参数）;
  System.out.println(变量名称);
  ```

  

方法执行的步骤是：

| **1、根据方法名称，找到方法体**   |
| :-------------------------------- |
| **2、参数传递**                   |
| **3、执行方法体**                 |
| **4、带着返回值回到方法的调用处** |

** 注意： 对于有返回值的方法可以使用三种调用方式，对于无返回值的方法，只能使用单独调用。

## 定义方法的三要素

* 返回值类型
* 方法名
* 参数列表

 

* 一个有返回值的方法中可以有多个return,但是必须保证有且仅有一个return会被执行。两个return不能连写。
