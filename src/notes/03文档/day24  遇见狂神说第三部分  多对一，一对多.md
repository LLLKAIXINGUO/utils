# day24  遇见狂神说第三部分  多对一，一对多

教师与学生 

对于学生而言： **关联** 多个学生关联一个教师   【多对一】   多个关联一个

对于老师而言：  **集合** 一个老师下 有多个学生【一对多】    一个下有多个



实践 

1. 拷贝工具类以及配置文件和数据库连接信息

2. 创建student  和 teacher实体类

3. 创建其对应的Mapper文件

4. 在resources下创建其对应的xml文件

5. 在mybatis-config.xml中绑定我们的Mapper接口，或者文件

   ```xml
   <mappers>
       <mapper resource="com/lym/dao/*Mapper.xml"/>
   </mappers>
   ```

6. 写个测试类验证是否正确



查询学生类的结果集是

Student{id=1, name='小明', teacher=null}
Student{id=2, name='小红', teacher=null}
Student{id=3, name='小张', teacher=null}
Student{id=4, name='小王', teacher=null}

解决teacher=null的问题

* 1.按照查询嵌套处理

* 思路：1.查询所有的学生信息，， 根据查询出来的学生的tid,再查对应的教师

* ```xml
  <mapper namespace="com.lym.dao.StudentMapper">
      <select id="getListStudent" resultMap="teacherstudent">
          select * from student
      </select>
      <resultMap id="teacherstudent" type="Student">
          <id property="id" column="id"/>
          <result property="name" column="name"/>
          <association property="teacher" column="tid" javaType="Teacher" select="getTeacher"/>
          此个association  javaType是实际类型，select是相当于嵌套查询语句
      </resultMap>
      
      <select id="getTeacher" resultType="com.lym.domain.Teacher">
          select * from teacher where id=#{id}
      </select>
      
  </mapper>
  ```
  
* 

* 2.按照结果嵌套处理

  ```xml
  <select id="getAll2" resultMap="st">
      select s.id sid,s.name sname,t.name tname,t.id tid
      from student s,teacher t
      where s.tid=t.id
  </select>
  <resultMap id="st" type="Student">
      <id column="sid" property="id"/>
      <result column="sname" property="name"/>
      <association property="teacher" javaType="Teacher">
          这个association就是直接使用对象名，对象的真实类型，然后将其真正的数据库字段映射进来
          <result column="tid" property="id"/>
          <result column="tname" property="name"/>
      </association>
  </resultMap>
  ```

在resultMap中，特殊的对象我们要单独处理

**对象：association**    多对一





**集合：collection **       一对多

```xml
<select id="getTeacher" resultMap="teacherStudent">
    select s.id sid,s.name sname,t.name tname,t.id tid
    from student s,teacher t
    where s.tid=t.id and t.id=#{tid}
</select>
<resultMap id="teacherStudent" type="Teacher">
    <id property="id" column="tid"/>
    <result property="name" column="tname"/>
    <collection property="students" ofType="Student">
        <result property="id" column="sid"/>
        <result property="name" column="sname"/>
        <result property="tid" column="tid"/>
    </collection>
</resultMap>
```



