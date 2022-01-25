# day04 springboot   整合druid数据源     遇见狂神说

1. 导包

2. ```xml
     <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-jdbc</artifactId>
           </dependency>
   <dependency>
       <groupId>com.alibaba</groupId>
       <artifactId>druid</artifactId>
       <version>1.2.6</version>
   </dependency>
   ```

3. 配置yaml文件

4. ```yaml
   spring:
     datasource:
       username: root
       password: 1234
       url: jdbc:mysql://localhost:3306/mybatis?useUnicode=truecharacterEncoding=utf-8
       driver-class-name: com.mysql.jdbc.Driver
       type: com.alibaba.druid.pool.DruidDataSource
   
       #spring默认是不注入这些属性值的，需要自己绑定
       #druid 数据源专有配置
       initialSize: 5
       minIdle: 5
       maxActive: 20
       maxWait: 60000
   
       #配置监控统计拦截的filters stat:监控统计 、log4j、wall
       filters: stat
   ```

5. 写增删改查的方法做出测试

6. ```java
   @RestController
   public class JdbcController {
       @Autowired
       JdbcTemplate jdbcTemplate;
   
       //查询数据库的所有信息
       //没有实体类，数据库中的东西 ， 的获取方式，  map
       @GetMapping("/userList")
       public List<Map<String,Object>> userList(){
           String sql="select * from user";
           List<Map<String,Object>> list_maps = jdbcTemplate.queryForList(sql);
           return list_maps;
       }
       @RequestMapping("/addUser")
       public String addUser(){
           String sql="insert into user(id,name,pwd) values(8,'xi','12346')";
           jdbcTemplate.update(sql);
           return "add_ok";
       }
       @RequestMapping("/updateUser/{id}")
       public String updateUser(@PathVariable("id")int id){
           String sql = "update user set name=?,pwd=? where id="+id;
           Object[] obj = new Object[2];
           obj[0]="wangxiansheng";
           obj[1]="wang先的密码";
   
           jdbcTemplate.update(sql,obj);
           return "udpate_ok";
       }
       @RequestMapping("/delUser/{id}")
       public String deluser(@PathVariable("id")int id){
           String sql = "delete from user where id=?";
           jdbcTemplate.update(sql,id);
           return "delete_ok";
       }
   }
   ```

7. 创建config ----》DruidConfig

8. ```java
   @Configuration
   public class DruidConfig {
   
       //绑定到yaml配置文件中的datasource
       @ConfigurationProperties(prefix = "spring.datasource")
       @Bean
       public DataSource druidDataSource(){
           return new DruidDataSource();
       }
   
       //后台监控 : web.xml   ServletRegistrationBean
       //因为springboot内置了servlet容器，所以没有web.xml，替代方法：ServletRegistrationBeann
       @Bean
       public ServletRegistrationBean statViewServlet(){
           ServletRegistrationBean<StatViewServlet> bean = new
                   ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
           //后台需要有人登录，账号密码配置
           HashMap<String,String> initParameters = new HashMap<>();
           //增加配置
           //这里的loginUsername loginPassword  的key是固定的
           initParameters.put("loginUsername","admin");
           initParameters.put("loginPassword","123456");
   
           //允许谁可以访问
           initParameters.put("allow","");
           //禁止谁能访问
   
           bean.setInitParameters(initParameters);//设置初始化参数
           return bean;
       }
   ```

9. 执行了sql语句后， 进入到 druid路径下  即可看到监控状态