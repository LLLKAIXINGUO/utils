#  day12  第一部分  xml

## xml

1. 概念             【可扩展标记语言 】 可扩展：标签都是自定义的
2. 语法
3. 解析



**功能：**

* 存储数据
  1. 配置文件
  2. 在网络中传输

**xml与html的区别：**

1. xml的标签都是自定义的，html标签是预定义
2. xml语法严格，html语法松散
3. xml是存储数据的，html是展示数据 

**语法**

* 基本语法

  1. xml文档的后缀名  .xml
  2. xml第一行必须定义为文档声明   //必须顶在最第一行的开始地方
  3. xml文档中有且只有一组根标签
  4. 属性值必须使用引号（单双都可）引起来
  5. 标签必须正确关闭
  6. xml的标签名称区分大小写

  ```xml
  <?xml version='1.0' ?>
  <users>
  
  ​	<user id='1'>
  
  ​		<name>dsfds</name>
  
  ​		<age>15</age>
  
  ​	</user>
  
  	<user id='2'
  ​		<name>dsfds</name>
  
  ​		<age>1</age>
  
  ​	</user>
  
  </users>
  ```

  **组成部分：**

  1. 文档声明

     * <?xml  属性列表 ?>
     * 属性列表
       * version:版本号，必须的属性
       * encoding:编码方式 。     告知解析引擎当前文档使用的字符集，默认值：ISO-8859-1
       * standalone:是否独立     值为yes【不依赖其他文件】  或no【依赖其他文件】

  2. 指令 ：（了解）结合css的

  3. 标签：  自定义

  4. 属性：   id属性值唯一

  5. 文本         CDATA区域，  其中的内容会被原样展示

     * 格式：       <>       <!>       <![]>      <![CDATA[数据]]>

  6. 约束： 规定xml文档的书写规则

     * 作为框架的使用者（程序员）：  要求：
       * 1.能够在xml中引入约束文档
       * 2.能够简单的读懂约束文档

     * 说明文档：【约束文档】  规定xml文档的书写规则
     * 分类：  
       * DTD:一种简单的约束技术
       * Schema:一种复杂的约束技术
     * DTD:
       * 内部dtd：将约束规则定义在xml文档中
       * 外部dtd；将约束的规则定义在外部的dtd文件中
         * 本地  <!DOCTYPE  根标签名  SYSTEM  "dtd文件的位置">
         * 网络<!DOCTYPE  根标签名 PUBLIC  "dtd文件的名字" "dtd文件的位置URL">
       * 
       * 
       * 
       * 
       * 
     * Schema:  可以做到内容的限定                 后缀名，是xsd 

3.解析    ：操作xml文档，将文档中的数据读取到内存中

* 操作xml文档
  1. 解析（读取）：将文档中的数据读取到内存中
  2. 写入：将内存中的数据保存到xml文档中。持久化的存储
* 解析xml的方式 
  1. DOM:(将标记语言文档**一次性加载**进内存，在内存中形成一颗dom树)
     * 优点; 操作方便，可以对文档进行CRUD的所有操作
     * 缺点：占内存
  2. SAX：逐行读取，基于事件驱动的 
     * 优点：不占内存
     * 缺点：只能读取 ，不能增删改
* 服务端一般使用dom的思想，，移动端一般使用sax



* xml常见的解析器
  1. jaxp：sun公司提供的解析器，支持dom和sax两种
  2. dom4j:一种非常优秀的解析器
  3. jsoup：是一款java的html解析器，可直接解析某个URL地址、HTML文本内容。它提供了一套非常省力的API，可同各国DOM，CSS以及类似于Jquery的操作方法来取出和操作数据
  4. PULL:  安卓操作系统内置的解析器，sax方式的

jsoup：是一款java的html解析器，可直接解析某个URL地址、HTML文本内容。它提供了一套非常省力的API，可通过DOM，CSS以及类似于Jquery的操作方法来取出和操作数据

**快速入门**        步骤：

1. 导入jar包
2. 获取Document对象
   1. 根据xml文档获取
3. 获取对应的标签Element对象
4. 获取数据

```java
//文档中要先有这个xml文件       
//获取Document对象，根据xml文档获取
//           1.获取student.xml的path路径
        String path = JsoupDemo01.class.getClassLoader().getResource("student.xml").getPath();
        System.out.println(path);

        //2.解析xml文档，加载文档进内存 ，获取dom树-----》document
        Document document = Jsoup.parse(new File(path), "utf-8");
        //获取元素对象
        Elements elements = document.getElementsByTag("name");
        System.out.println(elements.size());
        Element element = elements.get(0);
        String text = element.text();
        System.out.println(text);
    } 
```

**jsoup工具类 ：  有意思，以后可以用来做爬虫小程序来用**

