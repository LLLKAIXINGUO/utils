# day08 第一部分 File类、递归

### **File类**   

对文件夹和文件进行一些列的操作     java.io.File类时文件和目录（文件夹）路径名的抽象表示，主要用于文件和目录的创建、查找和删除等操作。

**File类重点记住的三个单词**

* file  文件
* directory    文件夹/目录
* path    路径



**静态成员变量**

*  **static   String   pathSeparator**  与系统有关的路径分隔符，为了方便，它被表示为一个字符串

* **static  char  pathSeparatorChar**   与系统有关的路径分隔符

* **static  String  separator**    与系统有关的默认名称分隔符，为了方便，它被表示为一个字符串

* **static char   separator**             与系统有关的默认名称分隔符

* ```java
  //静态方法，直接类名.方法名直接调用
  String pathSeparator = File.pathSeparator;
  System.out.println(pathSeparator);//   ; windows上分号，linux 冒号
  char pathSeparatorChar = File.pathSeparatorChar;
  System.out.println(pathSeparatorChar);//   ;
  String separator = File.separator;
  System.out.println(separator);//          \ windows上反斜杠，linux 斜杠
  char separatorChar = File.separatorChar;
  System.out.println(separatorChar);//          \  
  ```

**路径：**

* 绝对路径  ：是一个完整的路径     从盘符开始（c: d:）
* 相对路径 ： 是一个简化的路径
  * 相对指的是相对于当前项目的目录 ，如果使用当前项目的跟目录，路径可以简化书写
    * **注意：**
      * 1.路径是不区分大小写
      * 2.路径中的文件名称分隔符windows使用反斜杠，反斜杠是转义字符，两个反斜杠代表一个普通的反斜杠

##### **File类的构造方法**

**File（String  pathname）** 通过将给定路径名字符串转换为抽象路径名来创建一个新的File实例

**参数：**

* String  pathname : 字符串的路径名称
* 路径可以是以文件结尾，也可以是以文件夹结尾
* 路径可以是相对路径，也可以是绝对路径
* 路径可以是存在，也可以是不存在
* 创建File对象，只是把字符串路径封装为File对象，不考虑路径的真假情况



**File（String  parent,String child）**  根据parent路径名字符串和child路径名字符串创建一个新File实例

**参数：** 把路径分为两部分

String   parent    ：  父路径

String   child     :        子路径

**好处：** 父路径和子路径，可以单独书写，使用起来非常灵活；父路径和子路径都可以变化

**File（File  parent,String child） ** 根据parent路径名字符串和child路径名字符串创建一个新File实例

**参数：** 把路径分为两部分

File   parent    ：  父路径

String   child     :        子路径

**好处：**

​		父路径和子路径，可以单独书写，使用起来非常灵活；父路径和子路径都可以变化

​		父路径是File类型，可以使用File的方法对路径进行一些操作，再使用路径创建对象



**File类中的常用方法**

* public  String  getAbsolutePath():   返回此File的绝对路径字符串
* public  String  getPath():    将此File转换为路径字符串
* public  String  getName():     返回此File表示的文件或目录的结构
* public  Long  length():           返回由此File表示文件的长度  

```java
File file = new File("D:\\ideaproject\\increase\\a.txt");
String absolutePath = file.getAbsolutePath();
System.out.println(absolutePath);//D:\ideaproject\increase\a.txt
String path = file.getPath();
System.out.println(path);//D:\ideaproject\increase\a.txt
String name = file.getName();
System.out.println(name);//a.txt
long length = file.length();
System.out.println(length);//0  返回0代表构造方法给出的路径不存在
```

file类的判断方法

* 　**public   boolean  exists();**此File 表示的文件或目录是否实际存在。

​				用于判断构造方法中的路径是否存在

存在：true

不存在：false

* public  boolean isFile();此file是否为文件
* public  boolean isDirectory();此file是否为文件
  * 这两个方法的使用前提，路径必须是存在的，否则都返回false



**file类的创建删除方法**

* public  Boolean createNewFile();当且仅当具有该名称的文件尚不存在时，创建一个新的空文件        这个方法需要抛出异常
  * 注意：此方法只能创建文件，不能创建文件夹
  * 创建文件的路径必须存在，否则会抛出异常
* public boolean  delete();  删除由此File表示的文件和目录
* public  boolean  mkdir(); 创建由此file表示的目录  创建单级文件夹
* public  boolean  mkdirs();创建由此File表示的目录，包括任何必须但不存在的父目录       创建多级文件夹



**File类遍历（文件夹）目录功能**

* * **public  String[]   List();**  返回一个String 数组，表示该File目录中的所有子文件或目录
  * **public File[]  listFiles();** 返回一个File数组，表示该File目录中的所有的子文件或目录

  **注意：** list方法和listFile方法遍历的是构造方法中给出的异常

  * 如果构造方法中给出的目录的路径不存在，会抛出空指针异常
  * 如果构造方法中给出的路径不是一个目录，也会抛出空指针异常

# **递归**    

由方法自己调用自己的这种现象

**递归的分类：**  递归分为两种

* 直接递归      称为方法自身调用自己
* 间接递归      可以A方法调用B方法，B方法调用C方法，C方法调用A方法

**注意事项：**

* 递归一定要有条件限制，保证递归能够停下来，否则会发生栈内存溢出
* 在递归中虽然有限定条件，但是递归次数不能太多，否则也会发生扎按内存溢出
* 构造方法禁止递归

**递归的使用前提：**

* 当调用方法的时候，方法的主体不变，每次调用方法的参数不同，可以使用递归

**练习：**

* 使用递归计算1-n的和

  **使用递归必须明确**

  1. 递归的结束条件    【获取到1的时候结束】
  2. 递归的目的           【获取下一个被加的数字】

```java
public static int sum(int n){
    if(n==1){
        return 1;
    }
    return n+=sum(n-1);
}
```

**练习：**

递归遍历打印多级目录

```java
File fi = new File("D:\\ideaproject\\aaa");
        getAll(fi);
    }
    public static void getAll(File dir){
        System.out.println(dir);
        File[] files = dir.listFiles();
        for (File f : files) {
            if(f.isDirectory()){
                getAll(f);
            }
            else {
                System.out.println(f.getName());
            }
        }
```

**综合案例：**搜索指定文件

```java
  File fi = new File("D:\\ideaproject\\aaa");
    getAll(fi);
}
public static void getAll(File dir){
    File[] files = dir.listFiles();
    for (File f : files) {
        if(f.isDirectory()){
            getAll(f);
        }
        else {
            String name = f.getName();
            if(
                .endsWith(".java")){
                System.out.println(f);
            }
        }
    }
```

**使用过滤器来实现**

java.io.FileFiler接口：用于抽象路径名(File对象)下的过滤器

**File[] listFiles(FilenameFilter filter)**

作用：用来过滤文件的方法

抽象方法：用来过滤文件的方法

**boolean accept (File pathname)**测试指定抽象路径名是否应该包含在某个路径名列表中

参数： File  pathname:使用ListFiles方法遍历目录，得到的每一个文件对象

**注意：**两个过滤器接口是没有实现类的，需要自己定义其实现类，重写过滤方法

**过滤器的实现以及Lambda表达式**