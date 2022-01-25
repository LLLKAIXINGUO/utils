# day02 第二部分  JDBC连接池& jdbcTemplete

**数据库连接池**：其实就是一个容器（集合），存放数据连接的容器

​      当系统初始化好后，容器被创建，容器中会申请一些连接对象，当用户来访问数据库时，从容器中获取连接对象，用户访问完之后，会将连接对象归还给容器

* 好处：
  1. 节约资源
  2. 用户访问高效



* **实现**
  * 1.标准接口： DataSource        javax.sql包下的
    * 方法：
      * 获取连接：getConnection()
      * 归还连接：如果连接对象Connection是从连接池中获取的，那么调用Connection.close()方法，则不会载关闭连接了，而是归还连接
  * 2.一般我们不去实现它，有数据库厂商来实现
    1. C3P0：数据库连接池技术
    2. Druid: 数据库连接池实现技术，由阿里巴巴提供的



**C3p0的基本使用**

* **使用步骤：**

  1. 导入jar包   c3p0-0.9.5.2.jar      mchange-commons-java-0.2.12.jar【c3p0的依赖jar包】，不要忘记导入数据库的驱动jar包

  2. 定义配置文件 c3p0.properties      or        c3p0-config.xml 【会自动的去寻找这两个文件】

     ​        **路径：**直接将文件放在src目录下即可

  3. 创建连接池对象     数据路连接对象  ComboPooledDataSource

     ```java
     ComboPooledDataSource的参数为空，则是使用默认的 不想使用默认的，则使用配置文件中的name  named-config name="otherc3p0"
     ```

  4. 获取连接： getConnection



**Druid的使用步骤**

1. 导入jar包         druid-1.0.9.jar   ，    数据库驱动jar包
2. 定义配置文件
   * 是properties形式的
   * 可以叫任意名称，可以放在任意目录下
3. 加载配置文件 ，， 使用properties集合  
4. 获取数据库连接对象：  通过工厂类来获取的 DruidDataSourceFactory
5. 获取连接 ：  getConnection



**定义一个工具类**

1. 定义一个类 JDBCUtils
2. 提供静态代码块加载配置文件，初始化连接池对象
3. 提供方法
   1. 获取连接方法：通过数据库连接池获取连接
   2. 释放资源
   3. 获取连接池的方法

```java
private static DataSource ds;
static {
    Properties pro = new Properties();
    try {
        pro.load(JDBCUtils.class.getResourceAsStream("druid.properties"));
        ds = DruidDataSourceFactory.createDataSource(pro);
    } catch (IOException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public static Connection getConnection() throws SQLException {
    return ds.getConnection();
}
public static void close(Statement stmt,Connection conn){
    if(stmt!=null){
        try {
            stmt.close();
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
public static void close(ResultSet rs,Statement stmt, Connection conn){
    if(rs!=null){
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    if(stmt!=null){
        try {
            stmt.close();
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
public static DataSource getDatasource(DataSource ds){
    return ds;
}
```



**Spring  JDBC **       : Spring框架对JDBC的简单封装，提供一个JDBCTemplate对象简化JDBC的开发

**步骤**

1. 导入jar包

2. 创建JdbcTemplate对象，依赖于数据源DataSource

   * JdbcTemplate  template  =  new JdbcTemplate(ds);

3. 调用JdbcTemplate的方法来完成CRUD的操作

   * update()  执行DML语句    增、删、改 语句

   * queryForMap() 查询结果将结果封装为map集合

     * **注意：**这个方法查询的结果集长度只能是1

   * querryForList();查询结果，将结果封装为list集合

   * query(); 查询结果，将结果封装为JavaBean对象

     * ```java
       JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDatasource());
       String sql="select * from user";
       List<User> query = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
       for (User user : query) {
           System.out.println(user);
       }
       query的参数 参数1sql语句，参数2new BeanPropertyRowMapper<封装的类>(User.class/封装的类的字节码文件)
       //User{id=1, name='null', age=0}
       //User{id=2, name='null', age=0}
       //User{id=3, name='null', age=0}
       //User{id=4, name='null', age=0}
       ```

   * queryForObject(); 查询结果，将结果封装为对象

     * 一般用来查询聚合函数

   ```java
   JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDatasource());
   String sql = "select * from user where id=?";
           Map<String, Object> stringObjectMap = template.queryForMap(sql, 1);
           System.out.println(stringObjectMap);
   //{id=1, username=张三, password=123456}
   ```



```java
String sql ="select * from user";
List<Map<String, Object>> maps = template.queryForList(sql);
for (Map<String, Object> map : maps) {
    System.out.println(map);
}
//{id=1, username=张三, password=123456}
//{id=2, username=李四, password=111}
//{id=3, username=王五, password=123}
//{id=4, username=小张, password=987654} 将查询出来的的结果封装为map集合，然后加载进list集合中
```

**JDBC Templete**