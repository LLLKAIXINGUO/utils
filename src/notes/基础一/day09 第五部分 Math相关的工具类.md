# day09 第五部分 Math相关的工具类

public static double abs(double num);//获取绝对值

public static double ceil(double num);//向上取整

public static double floor(double num);//向下取整

public static long round(double num);//四舍五入

Math.PI  代表近似的圆周率常量

```java
double num = -12.3;
int a=-10;
System.out.println(Math.abs(num));
double ceil = Math.ceil(num);
double floor = Math.floor(num);
long round = Math.round(num);
System.out.println(ceil);
System.out.println(floor);
System.out.println(round);
System.out.println(Math.PI);
```



数学题：//计算在-10.8到5.9之间，绝对值大于6或者小于2.1的整数有多少个

```java
double min=-10.8;
double max=5.9;
int count=0;
for(int i=(int)min;i<max;i++){
    int a=Math.abs(i);
    if(a>6||a<2.1){
        count++;
    }
}
System.out.println(count);
```

