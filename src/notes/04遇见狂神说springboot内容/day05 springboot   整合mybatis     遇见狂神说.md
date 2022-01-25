# day05 springboot   整合mybatis     遇见狂神说

1. 新建一个项目 springboot   web  jdbc   mysql  的驱动选择上

2. 导入依赖坐标，整合包  mybatis-spring-boot-starter    

3. 连接数据库

   ```properties
   spring.datasource.username=root
   spring.datasource.password=1234
   spring.datasource.url=jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   ```

4. 在测试类输出，看是否连接到了数据库

   ```
   @Autowired
   DataSource dataSource;
   
   @Test
   void contextLoads() throws SQLException {
       System.out.println("-----------------");
       System.out.println(dataSource.getClass());
       System.out.println(dataSource.getConnection());
   
   }
   ```

5. 写实体类  user

6. 写接口mapper    UserMapper

   ```java
   @Mapper  // 这个注解表示了这是一个mybatis的mapper类
   public class UserMapper {
       List<User> queryUserList();
       User queryUserById(int id);
       int addUser(User user);
       int updateUser(User user);
       int deleteUser(int id);
   }                //或者在启动类上加入@MapperScan("com.lym.mapper")
   ```

7. 在resources下创建mybatis-->UserMapper .xml