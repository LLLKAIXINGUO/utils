# day10 第一部分 缓冲流、转换流、序列化流

#### **缓冲流**： 也叫高效流

**字节缓冲流：**  `BuffereInputStream`  `BufferedOutputStream`   

**字符缓冲流：** `BuffereReader`  `BuffereWriter`

**缓冲流的基本原理：** 是在创建流对象时，会创建一个内置的默认大小的缓冲区数组，通过缓冲读写，减少系统IO次数，从而提高读写的效率

### 字节缓冲流

###### 构造方法

* `public  BufferInputStream(InputStream  in)`:创建一个新的缓冲输入流
* `public  BufferOutputStream(OutputStream  out)`:创建一个新的缓冲输出流

**参数：**

* * * OutputStream  out ：字节输出流 
    * 我们可以传递FileOutputStream,缓冲流会给FileOutputStream增加一个缓冲区，提高FileOutputStream的写入效率
    * int  size：指定缓冲流内部缓冲区的大小，不指定默认

使用步骤（**重点**）

1. 创建FileOutputStream对象，构造方法中绑定要输出的目的地
2. 创建BufferOutputStream对象，构造方法中传递FileOutputStream对象爱过你，提高FileOutputStream对象效率
3. 使用BufferOutputStream对象中的方法write,把数据写入到内部的缓冲区中
4. 使用BuffereOutputStream对象中的方法flush，把内部缓冲区的文件，刷新到文件中
5. 释放资源（会先调用flush方法刷新数据，所以第四步可以省略）

