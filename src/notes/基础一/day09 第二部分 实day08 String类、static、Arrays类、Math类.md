# day09 第二部分 实day08 String类、static、Arrays类、Math类

## 字符串

1.  #### ==字符串的内容永不可变【重点】==

2. 正是因为字符串不可改变，所以字符串是可以共享使用的

3. 字符串在效果上相当于char[]字符数组，但是底层原理是byte[]字节数组【计算机中存贮的一切数据皆是字节】

#### 创建字符串的3+1种方法：

#### 三种构造方法

1. public String(); 创建一个空白字符串，不含有任何内容

   ```java
   String str1=new String();
   System.out.println("第一个字符串是："+str1);
   ```

   

2. public String(char[] array);根据字符数组的内容，来创建对用的字符串

   ```java
   char[] chars=new char[]{'A','B','C'};
   String str2=new String(chars);
   ```

3. public String(byte[] array);根据字节数组的内容，来创建对用的字符串

   ```java
   byte[] byteArray=new byte[]{97,98,99};
   String str3=new String(byteArray);
   ```

**一种直接创建**

==String str4="hello";==

* ==注意：直接写上双引号，就是字符串对象。==

###### # 字符串常量池.

* 程序当中直接写上的双引号字符串，就在字符串常量池中

1. 对于引用类型来说，==比较的是地址值。
2. 双引号直接写的字符串在常量池当中，new 的不在池当中。



### 字符串的常用方法

1. equals   equals方法是比较值的

2. equals方法具有对称性，也就是a.equald(b)和b.equals(a)效果一样

3. ==如果比较双方一个常量一个变量，推荐把常量字符写在前面。==

   因为如果将变量定义为null, 那么使用  常量在前输出为 false,而常量在后，输出为编译器 空指针异常

   ```java
   String str1=null;
   System.out.println("abc".equals(str1));//false
   System.out.println(str1.equals("abc"));//空指针异常
   ```

   推荐：”abc“.equals(str)      不推荐：str.equals("abc")

```java
String str1="Hello";
String str2="Hello";
char[] charArray = new char[]{'H','e','l','l','o'};
String str3=new String(charArray);
System.out.println(str1.equals(str2));//true
System.out.println(str1.equals(str3));//true
System.out.println("Hello".equals(str2));//true
System.out.println(str1.equals("Hello"));//true
System.out.println("hello".equals(str2));//false
```

* equalsIgnoreCase         英文字母忽略大小写比较值

* String**当中与获取相关的常用方法有**

1. public int length();获取字符串当中含有的字符个数，拿到字符串长度
2. public String concat(String str);将当前字符串和参数字符串拼接成为返回值新的字符串
3. public char charAt(int index);获取指定索引位置的单个字符。（索引从0开始）
4. public int inedxOf(String str);查找指定字符串在本字符串当中首次出现的索引位置，如果没有则返回 -1

```java
String str1="sabcdefghijk";
int length = str1.length();
System.out.println(length);//12
String str2 = str1.concat("klmopq");
System.out.println(str2);//sabcdefghijkklmopq
int indexOf = str1.indexOf("ef");
System.out.println(indexOf);//5
```

* 字符串的分割:按照参数的规则，将字符串切分成为若干部分。

```java
String str1="aaa,bbb,ccc";
String[] s = str1.split(",");
```

==如果使用  "."  分割的话，必须使用   " \\\\.”才可以==

* ​    String==========>char[]    toCharArray() 把字符串转换为一个一个的字符

  实现 通过键盘输入一个字符串 然后判断其中的 大写、小写、数字和其它字符出现的个数。 

```java
  Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个随机的字符串");
        String str1=scanner.next();
    int upperCase=0;
    int lowerCase=0;
    int number=0;
    int otherCase=0;
    char[] chars = str1.toCharArray();
    for (int i = 0; i < chars.length; i++) {
        if(chars[i]>='A'&&chars[i]<='Z'){
            upperCase+=1;
        }
        else if(chars[i]>='a'&&chars[i]<='z'){
            lowerCase+=1;
        }
        else if(chars[i]>='0'&&chars[i]<='9'){
            number+=1;
        }
        else
            otherCase+=1;
    }
    System.out.println("大写字母有"+upperCase+"个");
    System.out.println("小写字母有"+lowerCase+"个");
    System.out.println("数字有"+number+"个");
    System.out.println("其他字符有"+otherCase+"个");
}
```

