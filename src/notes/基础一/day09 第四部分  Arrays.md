# day09 第四部分  Arrays

* public static String toString(数组);  将参数数组变成字符串（按照默认格式：[元素1，元素2，元素3......]）

```java
int[] array={10,20,30};
//将int[]数组按照默认格式变成字符串
String str=Arrays.toString(array);
```

* public static **void** sort(数组);  按照默认升序对数组的元素进行排序

```java
int[] arrayInt=new int[]{3,2,1,15,8,20};
Arrays.sort(arrayInt);
System.out.println(Arrays.toString(arrayInt));//[1,2,3,8,15,20]
```

* 练习题：  随机输入一个字符串，   实现将其排序倒序输出

思路：首先应当将字符串转换为一个数组（toCharArray）

​            然后将其数组排序（Arrays.sort(数组名)）

​			使用for循环 倒序输出

```java
String str="asdfdhjfdsjkah784935";
char[] chars = str.toCharArray();
Arrays.sort(chars);
for (int i=chars.length-1;i>=0;i--){
    System.out.print(chars[i]);
}
```

