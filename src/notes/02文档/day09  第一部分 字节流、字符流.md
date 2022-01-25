# day09  第一部分 字节流、字符流

IO流              流：数据

**IO流：**

输入：把硬盘中的数据，读取带内存中使用

输出：把内存中的数据，写入硬盘内存入

## 字节流

一切文件数据（文本、图片、音频、视频等）在存储时，都是以二进制数字的形式存储。所以，字节流可以传输任意文件类型。

##### 字节输出流【OutputStream】

java.io.OutputStream  抽象类是表示字节输出流的所有的类的超类，将指定的字节信息写出到目的地。它定义了字节输出流的基本共性功能方法

* **public  void  close（）；**关闭此输出流并释放与此流相关联的任意系统资源
* **public void flush() ;**     刷新此输出流并强制任何缓冲的输出字节被书写
* **public void write(byte[] b);**将b.length字节从指定的字节数组写入此输出流
* **public void write（byte[] b, int  off,int len）;**从指定的字节数组写入len字节，从偏移量off开始输出到此输出流
* **public abstract void  write(int b);**将指定的字节输出流



**构造方法：**

FileOutPutStream(String name)  创建一个向具有指定名称的文件中写入数据的输出文件流

FlieOutputStream(File file) 创建一个向指定File对象表示的文件中写入数据的文件输出流

**参数：** 写入数据的目的

String name：目的地是一个文件的路径

File  file：目的地是一个文件

**构造方法的作用：**

1. 创建一个FileOutputStream对象
2. 会根据构造方法中传递的文件/文件路径，创建一个新的文件
3. 会把FileOutputStream对象指向创建好的文件



写入数据的原理（内存----》硬盘）

java程序------>jvm（java虚拟机）---->OS（操作系统）---->OS调用写数据的方法---->把数据写入到文件中



**字节输出流的使用步骤（重点）**

1. 创建一个FileOutputStream对象，构造方法中传递写入数据的目的地
2. 调用FileOutputStream对象中的方法write，把数据写入到文件中
3. 释放资源（流使用会占用一定的内存，使用完毕要把内存清空，提高程序的效率）

```java
FileOutputStream fos = new FileOutputStream("D:\\ideaproject\\aaa\\a.txt");
String s = "你好啊";       //将字符串转换为字节数组
byte[] bytes = s.getBytes();
fos.write(bytes);
System.out.println(Arrays.toString(bytes));
fos.close();
```

**追加写和换行写**

```java
FileOutputStream file = new FileOutputStream("D:\\ideaproject\\aaa\\a.txt",true);
String s = "你好，是我";
file.write(s.getBytes());
System.out.println(Arrays.toString(s.getBytes()));
file.close();       追加写
```

换行写：    写换行符

windows:     \r\n

linux        /n

mac            \r

```java
FileOutputStream file = new FileOutputStream("D:\\ideaproject\\aaa\\a.txt",true);
String s = "\r\nafdsfds\r\n";
byte[] bytes = s.getBytes();
file.write(bytes);
System.out.println(Arrays.toString(bytes));
file.close();
```





##### 字节输入流【InPutStream】

**构造方法：**

FileInputStream(String  name)

FileInputStream(File  file)

**参数**：读取文件的数据源

​				String  name:文件的路径

​				File file:文件

**构造方法的作用：**

1. 会创建一个FileInputStream对象
2. 会把FileInputStream对象指定构造方法中要读取的文件



**字节输入流的使用步骤**

1. 创建FileInputStream字节输入流对象，构造方法中绑定要读取的资源

2. 使用FileInputStream对象中的read方法，读取文件

     int    read()读取文件中的一个字节并返回，读取到文件的末尾返回-1

3. 释放资源

```java
 FileInputStream fi = new FileInputStream("D:\\ideaproject\\aaa\\q.txt");
int len=0;
while ((len=fi.read())!=-1){
    //((len=fi.read())!=-1)步进表达式
    //1.fi.read() 读取一个字符 2.(len=fi.read())把读取到的字符赋值给
    //变量len  3.((len=fi.read())!=-1)判断还有没有字符
    System.out.print((char)len);
}
 fi.close();
```



**字节输入流一次读取多个字节的方法**

* int  read(byte[] b)从输入流中读取一定数量的字节，并将其存储在缓冲区数组b中。
* * * **明确两件事**
    * 1.方法的参数byte[]的作用?
      * 起到缓冲作用，存储每次读取到的多个字节
      * 数组的长度一般定义为1024（1kb）或者1024的整数倍
    * 2.方法的返回值int是什么
      * 每次读取到的有效字符

```java
FileInputStream fs = new FileInputStream("D:\\ideaproject\\aaa\\q.txt");
byte[] bytes = new byte[5];
int read = fs.read(bytes);
System.out.println(read);//5
System.out.println(Arrays.toString(bytes));//[102, 115, 100, 115, 100]
System.out.println(new String(bytes));//fsdsd
fs.close();
```





**字节流练习：**  文件复制

实现原理：一读一写

**文件复制的步骤：**

1. 创建一个字节输入流对象，构造方法中绑定要读取的数据源
2. 创建一个字节输出流对象，构造方法中绑定我们要写入的目的地
3. 使用字节输入流对象中的方法read读取文件
4. 使用字节输出流中的方法write，把读取到的字节写入到目的地的文件中
5. 释放资源

**使用字节流读取中文文件的问题 **         使用字节流读中文会出现乱码问题

1个中文

CBK：占用两个字节

utf-8：占用三个字节



### **字符流**

###### **字符输入流：**     java.io.Reader ,是字符输入流的最顶层的父类，定义了一些共性的成员方法，是一个抽象类

**共性的成员方法**：

​		int  read()  读取单个字符并返回

​		int  read(char[]  cbuf) 一次读取多个字符，将字符读入数组

​		void  close() 关闭该流并释放与之关联的所有资源

​		

java.io.FileReader  **extends**  InputStreamReader  **extends**  Reader

FileReader :  文件字符输入流

**作用：** 把硬盘文件中的数据以字符的方式读取到内存中

**构造方法：**

​		FileReader(String  fileName)

​		FileReader(File file)

​		参数：读取文件的数据源 

​						String  fileName:文件的路径

​						File  file：一个文件

**FileReader构造方法的作用：**

1. 创建一个FileReader对象
2. 会把FileReader 对象指向要读取的文件

**字符输入流的使用步骤**

1. 创建FileReader对象，构造方法中绑定要读取的数据源
2. 使用FileReader对象中的方法read读取文件
3. 释放资源

```java
FileReader fd = new FileReader("D:\\ideaproject\\aaa\\q.txt");
int len=0;
char[] chars = new char[5];
while((len=fd.read(chars))!=-1){
    System.out.print(new String(chars,0,len));
}
fd.close();           //一次输出5个字符
```

**字符输出流**

java.io.Write:字符输出流，是所有字符输出流最顶层的父类，是一个抽象类

**共性的成员方法：**

​		void  write(int  c)写入单个字符

​		void  write(char[]  cbuf)写入字符数组

​		abstract  void  write(char[] cbuf,int off, int  len)写入字符数组的某一部分，off数组的开始索引，len写的字符个数

​		void  write(String  str,int  off,int len)写入字符串的某一部分，off字符串的开始索引，len写的字符个数

​		void  flush()刷新该流的缓冲

​		void  close()关闭该流，但要先刷新它

FileWriter:文件字符输出流

**作用：**把内存中字符数据写入到文件中

**字符输出流的使用步骤：（重点）**

1. 创建FileWriter对象，构造方法中共绑定要写入数据的目的地
2. 使用FileWriter中的方法write,把数据写入到内存缓冲区中（字符转换为字节的过程）
3. 使用FileWriter中的方法flush,把内存缓冲区中的数据，刷新到文件中
4. 释放资源（会先把内存缓冲区中的数据刷新到文件中）



flush和close方法的区别

​	-flush : 刷新缓冲区，该对象可以继续使用

​	-close： 先刷新缓冲区，然后通知系统释放资源，流对象不可以再被使用了



**字符流的续写和换行方法**

和字节输出流一样





使用properties集合    是唯一和io流相结合的集合

java.util.Properties集合   extends  Hashtable<k,v>  implements Map<k,v>

Properties  类表示了一个持久的属性集，Properties可保存在流中或从流中加载

Properties集合是一个唯一和IO流相结合的集合

​		可以使用Properties集合中的方法store,把集合中的临时数据，持久化写入到硬盘中存储

​		可以使用Properties集合中的方法load,把硬盘中保存的文件（键值对），读取到集合中使用

​    **属性列表中每个键及其对应值都是一个字符串**

* ​	Properties集合是一个双列集合，key和value默认都是字符串

properties集合有一些操作字符串的特有方法

​		**Object  setProperty(String key,String value)**调用Hashtable 的方法put

​		**getProperty(String key）**通过key找到value值，此方法相当于Map集合中的get(key)方法。

​		**Set<String>  stringPropertyNames()**  返回此属性列表中的键集，其中该键及其对应值是字符串此方法相当于Map集合中的keySet方法



可以使用Properties集合中的方法store , 把集合中的临时数据，持久化写入到硬盘中存储

* void  store(OutputStream out,String comments)
* void  store(Writer  writer , String comments)
  * **参数：**
    * OutputStream out ：字节输出流，不能写入中文
    * Writer  writer ：字符输出流，可以写中文
    * String comments ：用来解释说明保存的文件是做什么用的
      * 一般不能使用中文，会产生乱码，默认是unicode编码，一般使用“”空字符串

**使用步骤：**

1. 创建Properties集合对象，添加数据
2. 创建字节输出流/字符输出流对象，构造方法中绑定要输出的目的地
3. 使用Properties集合中的方法store，把集合中的临时数据，持久化写入到硬盘中存储
4. 释放资源

```java
Properties pro = new Properties();
pro.setProperty("迪丽热巴","155");
pro.setProperty("迪丽fjd","175");
pro.setProperty("迪丽热巴fds","180");
FileWriter fw = new FileWriter("D:\\ideaproject\\increase\\day09FileIOStream\\a.txt");
pro.store(fw,"data by save");
fw.close();
```

**load方法，可以把硬盘中的键值对文件，读取到集合中使用**

`void  load(InputStream  inStream)`

`void  load(Reader  reader)`

**参数**

InputStream  inStream:字节输入流，不能读取含有中文的键值对

Reader  reader：字符输入流，能读取含有中文的键值对

**使用步骤：**

1. 创建Properties 集合对象
2. 使用Properties集合对象中的方法load读取保存键值对的文件
3. 遍历Properties集合

**注意**

1. 存储键值对的文件中，键与值默认的连接符号可以使用= , 空格（其他符号）
2. 存储键值对的文件中，可以使用#进行注释，被注释的键值对不会再被读取
3. 存储键值对的文件中，键与默认都是字符串，不用再加引号

```java
Properties po = new Properties();
po.load(new FileReader("D:\\ideaproject\\aaa\\d.txt"));
Set<String> set = po.stringPropertyNames();
for (String key : set) {
    String value = po.getProperty(key);
    System.out.println(key+"="+value);

}
```

