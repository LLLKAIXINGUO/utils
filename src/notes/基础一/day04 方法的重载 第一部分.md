# day04 方法的重载 第一部分     p72

## overload:【多个方法的名称一样，参数列表不同。】

方法重载与下列因素有关：

* 参数个数不同
* 参数类型不同
* 参数的多类型顺序不同    eg :     (int a,double b)         (double a,int b)

方法重载与下列因素无关：

* 与参数名称无关       eg: (int a,int b)            (int x,int y)     
* 与方法的返回值类型无关         public static int / public static double