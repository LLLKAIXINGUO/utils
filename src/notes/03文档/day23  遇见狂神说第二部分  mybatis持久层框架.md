#  day23  遇见狂神说第二部分  mybatis持久层框架

## 作用域和生命周期，是至关重要的，因为错误的使用会导致非常严重的并发问题

**SqlSessionFactoryBuilder：**

* 一旦创建SqlSessionFactory,就不需要它了
* 局部变量

**SqlSessionFactory**：

* 可以想象为数据库连接池
* **SqlSessionFactory**一旦创建就应该在应用的运行期间一直存在 。 所以**SqlSessionFactory**的最佳作用域应该是application作用域
* 因此最简单的就是使用**单例模式**或者静态单例模式

**SqlSession**：

* 连接到连接池的请求
* Sqlsession的实例不是线程安全的，因此不能被共享，所以他的最佳作用域是请求或方法作用域
* 用完之后需要赶紧关闭，否则资源被占用



##### 解决属性名和字段名不一致的问题

数据库中的字段和实体类字段不一致

例如 把实体类中的    pwd，修改为password

1. 解决一 ：在xml中修改sql语句

select id,name,pwd as password from user where user

2.解决二  在xml中 使用**resultMap**结果集映射

```xml
<select id="getListUser" resultType="com.lym.pojo.User">
        select * from user
    </select>
//结果集映射
<resultMap id="UserMap" type="com.lym.domain.User">
    <!--        <result column="id" property="id"></result>-->
<!--        <result column="name" property="name"></result>-->
    这两条一样，所以根本就不需要映射也可以
    <result column="pwd" property="password"></result>
</resultMap>
<select id="getUserList" resultMap="UserMap">
    select * from user
</select>
```

上述为简单的resultMap配置。  困难的即是 一对多、多对多等





# 日志

 日志工厂

如果一个数据库操作，出现了异常，我们需要排错，日志就是最好的助手

* SLF4J（掌握）
* LOG4J
* LOG4J2
* STDOUT_LOGGING(掌握) 控制台输出

**在mybatis中具体使用哪一个日志实现，在设置中设定**

STDOUT_LOGGING标准日志输出

在配置文件中共   name属性必须这样写，错一个字都不行

```xml
<settings>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
    </settings>
```





**Limit**

select * from 表名 limit  startIndex,pageSize

startIndex 开始索引，从0开始



```java
List<User> getUserLimit(Map<String,Integer> map);
```



```xml
<select id="getUserLimit" parameterType="map" resultMap="UserMap">
    select * from user limit #{startIndex},#{pageSize}
</select>
```



```java
@Test
public void getUserLimit(){
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    Map<String, Integer> map = new HashMap<String, Integer>();
    map.put("startIndex",1);
    map.put("pageSize",3);
    List<User> userLimit = mapper.getUserLimit(map);
    for (User user : userLimit) {
        System.out.println(user);
    }
}
```



Rowbounds分页

### **分页插件**

### **PageHelper**     

Mybatis-plus是一个 MyBatis的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。



面向接口编程   根本原因：解耦，可扩展

面向对象是指：我们考虑问题时，以对象为单位，考虑它的属性及方法

面向接口是指：我们考虑问题时，以一个具体的流程（事务过程）为单位，考虑它的实现





### **使用注解开发**

使用java的注解来进行开发

```java
@Select("select * from user")
public List<User> getUserList();
```



```xml
<mappers>
    <mapper class="com.lym.dao.UserMapper"/>
</mappers>
```





**mybatis的详细执行流程**

0.导入maven依赖   MySQL  mybatis  junit

1. **Resources获取加载全局配置文件**
2. **实例化SqlSessionFactoryBuilder构造器**
3. 解析配置文件流XML ConfigBuilder
4. **SqlSessionFactory实例化**
5. transational事务管理
6. 创建executor执行器
7. **创建sqlSession**
8. **实现CRUD**



绑定接口      mybatis-config.xml  

```java
//配置参数为true实现自动提交      commit
public static SqlSession getSqlSession(){
    return sqlSessionFactory.openSession(true);
```



```java
@Select("select * from user")
public List<User> getUserList();
@Select("select * from user where id=#{id}")
User getUserById(@Param("id") int id);
@Insert("insert into user value(#{id},#{name},#{password})")
int saveUser(User user);
@Update("update user set name=#{name},pwd=#{password} where id=#{id}")
int updateUser(User user);
@Delete("delete from user where id=#{uid}")
int delUser(@Param("uid") int id);
```



关于@Param（）注解

* 基本类型的参数或者是String类型，需要加上
* 引用类型不需要加
* 如果只有一个基本类型的话，可以忽略，建议添加
* 我们在SQL中引用的就是我们这里的@Param()中设定的属性名



* ​         /#{}        ${}区别
* \#{}是预编译处理，$ {}是字符串替换   \#{}能够

