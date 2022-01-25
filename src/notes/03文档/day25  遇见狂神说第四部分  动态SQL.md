#  day25  遇见狂神说第四部分  动态SQL

动态**SQL**

**动态sql就是指根据不同的拼接条件生成不同的sql语句**

if

choose(when,otherwise)

trim(where,set)

foreach





**创建基础工程回顾：**

1. 导包
2. 编写配置文件
3. 编写实体类
4. 编写实体类对应Mapper接口和Mapper.XML文件





```java
public class IDUtils {
    public static String getID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
```

创建工具类  写getID方法，因为使用INnobe引擎，会导致如果把8删除了或者未创建成功，会下一个自增长为10



```xml
mybatis-config.xml文件 第一行设置为日志   第二行是将数据库中_匹配为驼峰命名法
<settings>
    <setting name="logImpl" value="STDOUT_LOGGING"/>
    <setting name="mapUnderscoreToCamelCase" value="true"/>
</settings>
```



```java
List<Blog> findAllBlogIF(Map<String,Object> map);
```

```xml
<select id="findAllBlogIF" parameterType="map" resultType="blog">
    select * from blog where 1=1
     <if test="title!=null">
        and title = #{title}
     </if>
    <if test="name!=null">
        and name = #{name}
    </if>
</select>
```





```xml
查询条件
相当于switch.....case
如果满足第一个choose走第一个choose条件就不往下走了
都不满足则使用默认条件 otherwise
<where>
    <choose>
        <when test="title!=null">
            title=#{title}
        </when>
        <when test="author!=null">
            author=#{author}
        </when>
        <otherwise>
            views=#{views}
        </otherwise>
    </choose>
</where>
```





```xml
<update id="updateBlog" parameterType="map">
    update blog
    <set>
       <if test="title!=null">
           title=#{title},
       </if>
        <if test="author!=null">
            author=#{author}
        </if>
    </set>
    where id=#{id}
</update>
```



##### **所谓的动态SQL，本质还是SQL语句，只是我们可以在SQL层面，去执行一个逻辑代码**



在mapper.xml文件中，使用sql标签进行抽取后，可以在需要使用的地方使用include标签引用即可



动态SQL之Foreach



## **缓存**

**缓存：**存在内存中的临时数据。

​	将用户经常查询的数据放在缓存（内存）中，用户去查询数据就不用从磁盘上（关系型数据库数据文件）查询；从缓存中查询，从而提高查询效率，解决了高并发系统的性能问题

**使用缓存**：减少和数据库的交互次数，减少系统开销，提高系统效率

**什么样的数据能使用缓存？？**？经常查询并且不经常改变的数据【可以使用缓存】



mybatis系统中默认定义了两级缓存：**一级缓存和二级缓存**

* 默认情况下，只有一级缓存开启。（SqlSession级别二点缓存，也称为本地缓存）
* 二级缓存需要手动开启和配置，是基于namespace级别的缓存
* 为了提高扩展性，Mybatis定义了缓存接口Cache。可以通过实现Cache接口来自定义二级缓存



缓存可用的清除策略有

* LRU   最近最少使用 ：移除最长时间不被使用的对象
* FIFO  先进先出：按对象进入缓存的顺序来移除他们
* 默认的清楚策略是LRU



**缓存失效的情况有**

* 查询两条不同的语句
* 增删改操作会刷新缓存
* 查询不同的mapper
* 手动清理缓存     sqlsession.clearcache()
* 一级缓存默认是开启的，只在一次SqlSession中有效，也就是拿到连接到关闭连接之间



**二级缓存**

* 二级缓存也叫全局缓存，一级缓存作用域太低了，所以诞生了二级缓存
* 基于namespace级别的缓存，一个名称空间，对应一个二级缓存
* 工作机制
  * 一个会话查询一条数据，这个数据就会被放在当前会话的一级缓存中
  * 如果当前会话关闭了，这个会话对应的一级缓存就没了；但是我们想要的是，会话关闭了，一级缓存的数据被保存到二级缓存中
  * 新的会话查询消息，就可以从二级缓存中获取内容
  * 不同的mapper查询的数据会放在自己对应的缓存（map）中

在mapper.xml文件中加入   一句 <cache/>

```xml
<cache
        eviction="FIFO"
        flushInterval="60000"
        size="512"
        readOnly="true"
    />  这个配置创建了一个FIFO缓存，每隔60秒刷新，最多可以存储对象或列表的512个引用，而且返回的对象被认为是只读的，因此对它们进行修改可能会在不同线程中的调用者产生冲突
```

步骤：

1. 开启全局缓存      在配置文件的settings   

```xml
<!-- 显式的开启全局缓存-->
<setting name="cachEnabled" value="true"/>
```

2. 在mapper.xml中加入<cache/>
3. 测试;   创建两个sqlsession，然后两个里面测试去写
4. 出现报错，应该在实体类中实现序列化

**总结**

* 只要开启了二级缓存，在同一个mapper下就有效
* 所有的数据都会先放在一级缓存中
* 只有当会话提交，或者关闭的时候，才会提交到二级缓存中



缓存执行顺序:

* 先看二级缓存中有没有
* 再看一级缓存中有没有
* 再查询数据库





Ehcache  使用第三方缓存