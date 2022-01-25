# day23  遇见狂神说第一部分  mybatis持久层框架

## 持久化

##### 数据持久化

* 持久化就是将程序的数据在持久状态和瞬时状态转化的过程。
* 数据库（jdbc）  io文件持久化        两种持久化方式
* 生活中持久化的：  冷藏，罐头

为什么需要持久化？？    因为内存 断电即丢 ， 由一些对象不能让他丢失，内存太贵了

## 持久层

* 完成持久化工作的代码块
* 层界限十分明显



需要mybatis的原因

* 帮助程序员把数据存入数据库中
* 方便
* 传统的JDBC代码太复杂了。 简化  框架  自动化
* 不用mybatis也可以。
* 优点，特性：简单易学，灵活，解除sql与程序代码的耦合，sql和代码的分离

**使用的人多**





思路：  **搭建环境----->  导入mybatis------->编写代码-------->测试**

1. 创建数据库表

2. 创建一个空的maven项目

3. 导入依赖

   ```xml
   <dependencies>
       <dependency>
           <groupId>org.mybatis</groupId>
           <artifactId>mybatis</artifactId>
           <version>3.5.2</version>
       </dependency>
       <dependency>
           <groupId>junit</groupId>
           <artifactId>junit</artifactId>
           <version>4.12</version>
           <scope>test</scope>
       </dependency>
       <dependency>
           <groupId>mysql</groupId>
           <artifactId>mysql-connector-java</artifactId>
           <version>5.1.48</version>
           <scope>runtime</scope>
       </dependency>
   </dependencies>
   
      <build>
   <!--        在build中配置resources,来防止我们资源导出失败的问题-->
           <resources>
               <resource>
                   <directory>src/main/resources</directory>
                   <includes>
                       <include>**/*.properties</include>
                       <include>**/*.xml</include>
                   </includes>
                   <filtering>true</filtering>
               </resource>
               <resource>
                   <directory>src/main/java</directory>
                   <includes>
                       <include>**/*.properties</include>
                       <include>**/*.xml</include>
                   </includes>
                   <filtering>true</filtering>
               </resource>
           </resources>
       </build>
   ```

4. 编写mybatis的核心配置文件

   * 在resources  下创建mybatis-config.xml文件

   ```XML
   <?xml version="1.0" encoding="UTF-8"?>
   <!DOCTYPE configuration
           PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-config.dtd">
   <configuration>
       <!-- 配置 mybatis 的环境 -->
       <environments default="mysql">
           <!-- 配置 mysql 的环境 -->
           <environment id="mysql">
               <!-- 配置事务管理的类型 -->
               <transactionManager type="JDBC"></transactionManager>
               <!-- 配置连接数据库的信息：用的是数据源(连接池) -->
               <dataSource type="POOLED">
                   <property name="driver" value="com.mysql.jdbc.Driver"/>
                   <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false&amp;serverTimezone=Asia/Shanghai"/>
                   <property name="username" value="root"/>
                   <property name="password" value="1234"/>
               </dataSource>
           </environment>
       </environments>
       
   </configuration>
   ```

5. 编写mybatis的工具类

   创建utils--------->MybatisUtils

   **每一个mybatis的应用都是以一个SqlSessionFactory的实例为核心的。SqlSessionFactory的实例可以通过SqlSessionFactoryBuilder获得。而SqlSessionFactoryBuilder则可以从XML配置文件或一个预先定制的Configuration的实例构建出SqlSessionFactory的实例**

   ```java
   private static SqlSessionFactory sqlSessionFactory;
       static {
           try {
               //获取sqlsessionfactory对象
               String resource = "mybatis-config.xml";
               InputStream inputStream = Resources.getResourceAsStream(resource);
               sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       //可以从这个工厂（SqlSessionFactory）中获取SqlSession的实例
       // SqlSession 完全包含了面向数据库执行SQL命令所需的所有方法，可以通过SqlSession实例来执行已映射的SQL语句
       public static SqlSession getSqlSession(){
           return sqlSessionFactory.openSession();
       }
   ```

**可以从这个工厂（SqlSessionFactory）中获取SqlSession  的实例。SqlSession 完全包含了面向数据库执行SQL命令所需的所有方法，可以通过SqlSession实例来执行已映射的SQL语句**







编写代码

1. 实体类

2. Dao接口

   ```java
   public interface UserDao {
       /**
        * 查询所有对象
        * @return
        */
   
       public List<User> getUserList();
   }
   ```

3. Dao的实现类由原来的UserDaoImpl转变为UserMapper.xml  写成配置文件xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.lym.dao.UserDao">
    <select id="getUserList" resultType="com.lym.domain.User">
        select * from user
    </select>
</mapper>

namespace 中的包名要和Dao/mapper 接口的包名一致
id  就是对应的namespace中的方法名
resultType Sql语句执行的返回值 Class
parameterType  参数类型
```

## 测试

**注意这个错误**org.apache.ibatis.binding.BindingException: Type interface com.lym.dao.UserDao is not known to the MapperRegistry.

**每一个Mapper.xml都需要在Mybatis核心配置文件中注册**

```xml
//在mybatis的核心配置文件中
<mappers>
    <mapper resource="com/lym/dao/UserMapper.xml"></mapper>
</mappers>
```



Caused by: org.apache.ibatis.exceptions.PersistenceException: 
### Error building SqlSession.
### The error may exist in com/lym/dao/UserMapper.xml

mavem由于他的约定大于配置，我们以后所写的配置文件，无法被导出或生效的问题，解决方案

在builder中配置resources,来防止我们资源导出失败的问题

```xml
    <build>
<!--        在build中配置resources,来防止我们资源导出失败的问题-->
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>
```



```java
@Test
public void test01(){
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    List<User> listUser = mapper.getListUser();
    for (User user : listUser) {
        System.out.println(user);
    }
}
```





1. 编写接口
2. 编写对应的xml
3. 测试  【增删改需要提交事务】

```java
dao-------->UserMapper
public interface UserMapper {
    public List<User> getListUser();
    User getUserById(int id);
    int saveUser(User user);
    int updateUser(User user);
    int delUser(int id);
}
```



```xml
dao--->UserMapper
<mapper namespace="com.lym.dao.UserMapper">
    <select id="getListUser" resultType="com.lym.pojo.User">
        select * from user
    </select>
    <select id="getUserById" parameterType="int" resultType="com.lym.pojo.User">
        select * from user where id=#{id}
    </select>
    <insert id="saveUser" parameterType="com.lym.pojo.User">
        insert into user(id,name,pwd) value (#{id},#{name},#{pwd})
    </insert>
    <update id="updateUser" parameterType="com.lym.pojo.User">
        update user set name=#{name},pwd=#{pwd} where id=#{id}
    </update>
    <delete id="delUser" parameterType="int">
        delete from user where id=#{id}
    </delete>
</mapper>
```



```java
//五个测试方法
@Test
    public void getListUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> listUser = mapper.getListUser();
        for (User user : listUser) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void getUserById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userById = mapper.getUserById(1);
        System.out.println(userById);
        sqlSession.close();
    }
//增删改需要提交事务
    @Test
    public void saveUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(5);
        user.setName("小王");
        user.setPwd("1234566");
        int i = mapper.saveUser(user);
        if(i>0){
            System.out.println("插入成功");
            sqlSession.commit();
        }
    }

    @Test
    public void updateUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(5);
        user.setName("小王aaa");
        user.setPwd("123456");
        int i = mapper.updateUser(user);
        if(i>0){
            System.out.println("修改成功");
            sqlSession.commit();
        }
    }

    @Test
    public void delUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.delUser(5);
        if(i>0){
            System.out.println("删除成功");
            sqlSession.commit();
        }
    }
```





**假设，我们的实体类，或者数据库中的表，字段或**



**参数过多，我们应当考虑使用Map**

```java
int saveUser22(Map<String,Object> map);
```



```xml
<insert id="saveUser22" parameterType="map">
    insert into user(name,pwd) value (#{username},#{userpwd})
</insert>
```



```java
@Test
public void saveUser22(){
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("username","zhangsan");
    map.put("userpwd","123");
    int i = mapper.saveUser22(map);
    if(i>0){
        System.out.println("插入成功");
        sqlSession.commit();
    }
}
```





**模糊查询**

```java
List<User> getUserLike(String value);
```



```xml
<select id="getUserLike" resultType="com.lym.pojo.User">
    select * from user where name like #{value}
</select>
```



```java
@Test
public void getUserLike(){
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    List<User> listUser = mapper.getUserLike("%张%");
    for (User user : listUser) {
        System.out.println(user);
    }
    sqlSession.close();
}
```





### **配置解析**

1. 核心配置文件
   * mybatis-config
     - configuration（配置）
       - properties（属性）
       - settings（设置）
       - typeAliases（类型别名）
       - typeHandlers（类型处理器）
       - objectFactory（对象工厂）
       - plugins（插件）
       - environments（环境配置）
         - environment（环境变量）
           - transactionManager（事务管理器）
           - dataSource（数据源）
       - databaseIdProvider（数据库厂商标识）
       - mappers（映射器）





#### environments（环境配置）:

#### properties（属性）:可以通过使用properties文件，可以外部配置替换







项目改造，**根据配置文件改造**

1. 改造1   properties   把配置文件创建为db.properties   然后都放进去

```
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/mybatis?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false&amp;serverTimezone=Asia/Shanghai
username=root
password=1234
//然后在核心配置文件中引入
```

在xml中，所有的属性都要遵守其顺序  所有properties属性要写在最上边



**别名的配置  **   

```xml
//单个类的配置
<typeAliases>
    <typeAlias type="com.lym.pojo.User" alias="User"/>
</typeAliases>
```



```xml
//整个包的文件配置别名，在没有注解的情况下 默认是类名的首字母小写的，若有注解，则别名为其注解值         @Alias("") 在类上添加这个注解
<typeAliases>
    <package name="com.lym.pojo"/>
</typeAliases>
```

**如果实体类较少的话，使用第一种，如果类较多，就使用包的方式来起别名，第一种可以DIY别名**



**<mappers>映射器**

1. 第一种方式: resource

   ```xml
   每一个mapper.xml都需要在mybatis核心配置文件中注册
   <mappers>
       <mapper resource="com/lym/dao/UserMapper.xml"/>
   </mappers>
   ```

2. 第二种方式: 使用class文件绑定注册

   ```xml
   每一个mapper.xml都需要在mybatis核心配置文件中注册
   <mappers>
           <mapper class="com.lym.dao.UserMapper"/>
    </mappers>
   ```

**注意点：**

* 接口和他的Mapper配置文件必须同名
* 接口和他的Mapper配置文件必须在同一个包下

3.第三种方式

```
 <mappers>
<!--        <mapper resource="com/lym/dao/UserMapper.xml"/>-->
<!--        <mapper class="com.lym.dao.UserMapper"/>-->
        <package name="com.lym.dao"/>
    </mappers>
```

**注意点：**

* 接口和他的Mapper配置文件必须同名
* 接口和他的Mapper配置文件必须在同一个包下

