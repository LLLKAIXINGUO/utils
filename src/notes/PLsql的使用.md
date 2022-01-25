# PLsql的使用 第一部分

打开后选中My objects

新建   Command Window

  select sysdate  from dual ;  查询当前日期



==单引号中的值严格区分大小写==

创建表的sql语句

首先输入 ed   打开文本编辑工具，书写创建表的语句  点击ok    输入/



desc  表名     列出表的列



用别名的三种方式    加上as    别名会变大写                                加上双引号  【若别名是多个单词构成，必须使用该方法】





连接符 ：

* 把列与列，列与字符连接在一起
* 用’||‘ 表示
* 可以用来’合成‘列    【用单引号连接字符串】

eg: select id || ' is ' || name;        输出    1  is fd



distinct   去重



   使用where 查询日期时候定义格式        如下所示

where    `to_char` (h_date【日期的属性名】,'yyyy-mm-dd')='1999-04-17'





PLSQL中   赋值符号是      ：=

  



between........and   在两个值之间，包括这两个值

where  salary  >=4000 and salary <=7000

where salary between 4000 and 7000 





in 

where d_id=10 or d_id=11 or d_id=12

where d_id in(10,11,12)





like

模糊查询

where  name  like '%a%' 

%任意个字符    _一个字符

匹配含有下划线的有哪些 就表示为本身的下划线     

where  name   like  '%\\_%'     escape  '\\'

where  name   like  '%#_%'     escape  '#'





匹配空值  is  null

匹配非空值    is  not  null





order by  排序字段 desc【降序】 asc 【升序】





**单行函数**  ：  字符    数值    日期         转换       通用【五种类型的】

* 操作数据对象
* 接受参数返回一个结果
* ==只对一行进行变换==
* ==每行返回一个结果==
* 可以转换数据类型
* 可以嵌套
* 参数可以是一列或一个值

字符 ： 例  select  lower('ASDFG'),UPPER('Asfdsf'),initcap('Asffds java')

​      输出   asdfg            ASFDSF        Asffds Java

lower全转小写      upper全转大写      initcap 首字母转大写

 LPAD(属性名，10【位数】，’*‘【补位符】)         eg:     ******************1234

RPAD（属性名，10【位数】，’*‘【补位符】）    eg:     1234******************





PLsql  **是对SQL语言存储过程语言的扩展**   是一种**过程处理语言**

**PL/SQL程序由三个块组成 ， 即==声明部分、执行部分、异常处理部分==**

pl/sql块的结构如下：

DECLARE

/*声明部分：在此声明PL/SQL用到的变量，类型及游标，以及局部的存储过程和函数 */

~BEGIN~

/*执行部分： 过程及SQL语句，即程序的主要部分*/

EXCEPTION

/*执行异常的部分：错误处理          when.....then....*/  

~END;~

其中，执行部分是必须的



==**PL/SQL写的时候应该先加上  set   servetoutput  on  回车  不写每次运行完了以后没有输出结果**==

**PL/SQL块可以分为三部分**

 dbms_output.put_line("hello,word");