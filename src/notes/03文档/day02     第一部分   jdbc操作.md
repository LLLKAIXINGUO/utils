# day02     第一部分   jdbc操作    

jdbc : java  Database  Connectivity       java 数据库连接，java语言操作数据库

jdbc:定义了一套操作所有关系型数据库的规则（接口），各个数据库厂商去实现这套接口，提供数据库驱动jar包，我们可以使用这套接口（jdbc）编程，真正执行的代码时驱动jar包中的实现类

jdbc快速入门

1. 导入驱动jar包
   1. 1第一步在项目文件夹下创建libs文件夹，放入数据库的连接jar包
   2. 右键文件夹，  Add  as library
2. 注册驱动
3. 获取数据库连接对象Connection
4. 定义sql语句
5. 获取执行sql语句的对象preparestament对象
6. 执行sql，接收返回结果
7. 处理结果
8. 释放资源

```java
//导入驱动jar包
//注册驱动
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "1234");
Statement statement = conn.createStatement();
String sql = "update user set name='小a' where id=1";
int i = statement.executeUpdate(sql);
System.out.println(i);
statement.close();
conn.close();
```



**详解JDBC中的五个对象**

1. DriverManager
   * 驱动管理对象
     * 1.注册驱动：告诉程序是用的哪个数据库的驱动
       * 通过查看源代码发现：在"com.mysql.jdbc.Driver"下存在静态代码块是注册驱动的
     * 2.获取数据库连接
       * static Connection getConnection(String url,String url,String password)
2. Connection
   * 数据库连接对象
     * 获取执行sql的对象
       * Statement createStatement(String sql)
       * PreparedStatement prepareStatement(String sql)
     * 管理事务
       * 开启事务： void setAutoCommit(boolean autoCommit) :调用该方法设置参数为false,即开启事务
       * 提交事务：commit()
       * 回滚事务:  srollback()
3. Statement : 用于执行静态sql
   * 执行sql的对象
     * boolean  execute(String sql):      可以执行任意的sql   【了解】
     * int  executeUpdate(String sql)   执行DML(insert、update、delete)语句、DDL（create   alter  drop）语句               **返回值是影响的行数**
     * ResultSet  executeQuerry(String sql)  执行DQL（sql）语句
4. ResultSet
   * 结果集对象
5. PreparedStatement
   * 执行sql对象

```java
Connection connection = null;
Statement statement = null;
try {
    Class.forName("com.mysql.jdbc.Driver");
    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "1234");
    statement = connection.createStatement();
    String sql = "delete from user where id=3";
    int i = statement.executeUpdate(sql);
    if (i > 0) {
        System.out.println("方法执行成功");
    } else {
        System.out.println("方法执行失败");
    }

} catch (ClassNotFoundException e) {
    e.printStackTrace();
} catch (SQLException e) {
    e.printStackTrace();
}finally {
    if(statement!=null) {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
//防止程序中断占用资源
```

**ResultSet：结果集对象，封装查询结果**

* next() : 游标向下移动一行
* getXxx（参数）  获取数据
  * Xxx :代表数据类型    如：     int  getInt()       ,      String  getString()
  * 参数
    * 1.int  ： 代表列的编号，从1开始  如：getString(1)
    * 2.String:代表列的名称   如getInt(age)

```java
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
try {
    Class.forName("com.mysql.jdbc.Driver");
    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "1234");
    statement = connection.createStatement();
    String sql = "select * from user";
    resultSet = statement.executeQuery(sql);
    resultSet.next(); //执行时，默认游标在表头上，时候next命令，光标定位到第一行
    String string = resultSet.getString(2);
    System.out.println(string);
} catch (ClassNotFoundException e) {
    e.printStackTrace();
} catch (SQLException e) {
    e.printStackTrace();
} finally {
    if (resultSet != null) {
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

```java
 Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "1234");
            statement = connection.createStatement();
            String sql = "select * from user";
            resultSet = statement.executeQuery(sql);
//            resultSet.next();
//            String string = resultSet.getString(2);
//            int id = resultSet.getInt(1);
//            System.out.println(id);
//            System.out.println(string);
            while (resultSet.next()){
                int id1 = resultSet.getInt(1);
                String string = resultSet.getString(2);
                int id2 = resultSet.getInt(3);
                System.out.println(id1+"   "+string+"   "+id2);
            }          //优化
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
```

###  

### **练习，定义一个方法findALL，使用集合将其封装为对象**

```java
private int id;
private String name;
private int age;
GETTER&&SETTER
TOstring
```

```java
List<User> all = new JDBCDemo06List().findAll();//测试非静态方法，所以需要创建对象
    System.out.println(all);
    for (User user : all) {
        System.out.println(user);
    }
    System.out.println(all.size());
}
public List<User> findAll(){
    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;
    List<User>  users = null;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "1234");
        statement = connection.createStatement();
        String sql = "select * from user";
        rs = statement.executeQuery(sql);
        User user = null;
        users = new ArrayList<User>();
        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            user = new User();  //封装成对象并赋值
            user.setId(id);
            user.setName(name);
            user.setAge(age);
            users.add(user);  //添加到集合中
        }
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }finally {
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return users;
```

### 抽取JDBC工具类：    JDBCUtils

* **目的：**简化书写
* **分析**
  1. 注册驱动也抽取
  2. 抽取一个方法获取连接对象
     * 需求：不传递参数，还得保证工具类的通用性，， 使用properties配置文件
  3. 抽取一个方法释放资源

```properties
url=jdbc:mysql://localhost:3306/db1
user=root
password=1234
driver=com.mysql.jdbc.Driver
```

```java
private static String url;
private static String name;
private static String password;
private static String driver;
//将成员变量创建为静态的，因为只有静态的变量才可以被静态的代码块，被静态的方法来访问

static{
    //读取资源文件，获取值
    //获取配置文件，只需要调用一次，写入在static静态代码块中

    try {
        //1.创建Properties集合类
        Properties pro = new Properties();
        //获取src路径下的文件方式----->ClassLoader  类加载器{可以用来加载字节码文件进内存
        // 而且可以获取src下文件的路径}
        ClassLoader classLoader = JDBCUtil.class.getClassLoader();
        //URL统一资源定位符，绝对路径
        URL resource = classLoader.getResource("db.properties");
        String path = resource.getPath();
        //2.加载文件 -----进内存
        pro.load(new FileReader("path"));
        //3.获取数据，赋值
        url = pro.getProperty("url");
        name = pro.getProperty("user");
        password = pro.getProperty("password");
        driver = pro.getProperty("driver");
        //4.注册驱动
        Class.forName(driver);
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

}
public static Connection  getConnection() throws SQLException {
       return DriverManager.getConnection(url,name,password);
    }
public static void close(Statement smt, Connection conn){
    if(smt!=null){
        try {
            smt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    if(conn!=null){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
public static void close(ResultSet rs,Statement smt, Connection conn){
    if(rs!=null){
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    if(smt!=null){
        try {
            smt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    if(conn!=null){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

####PreparedStatement   执行sql的对象

1. SQL注入问题 ：在拼接sql时，有一些sql的特殊关键字参与字符串的拼接，会造成安全性问题
   1. 输入用户随便 ， 输入密码   a 'or  ' a '  ='a
   2. sql:select * from user where username='fsdafafda' and password='a' or 'a'='a'
2. 解决sql注入问题：使用PreparedStatement对象来解决
3. 预编译的SQL：参数使用？作为占位符



1. 导入驱动jar包
   1. 1第一步在项目文件夹下创建libs文件夹，放入数据库的连接jar包
   2. 右键文件夹，  Add  as library
2. 注册驱动
3. 获取数据库连接对象Connection
4. 定义sql语句
   * 注意：sql语句的参数使用？作为占位符。  eg: select  * from user where  username=? and password=?
5. 获取执行sql语句的对象preparestament对象
   * 首先应该调用setXxx(参数1，参数2)方法给sql中的占位符赋值   
6. 执行sql，接收返回结果
7. 处理结果
8. 释放资源





JDBC控制事务

* 开启事务 setAutoCommit(boolean autoCommit) ; 调用该方法设置参数为false,即开启事务

* 提交事务 commit()

* 回滚事务 rollback()

  开启事务在执行sql语句之前 connection.setAutoCommit(false) ;  提交事务在执行sql语句之后，回滚事务语句放在  catch里 