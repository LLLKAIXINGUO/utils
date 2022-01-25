# 动力节点mybatisPLUS快速学习

1.案例书写（创建数据库表）

`create table User(`
`id int PRIMARY key,`
`name VARCHAR(30),`
`age int(10),`
`email VARCHAR(50))`

2.创建springboot项目 

3.导包

```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
            <version>5.1.48</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.4.3.4</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </dependency>
    </dependencies>
```

4.yaml 配置

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mybatisplus?useUnicode=true&characterEncoding=utf-8
    username: root
    password: 1234
    ##加入日志
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl    
```



批量删除使用lambda表达式的方式

![1638712189012](C:\Users\LLLKAIXINGUO\AppData\Roaming\Typora\typora-user-images\1638712189012.png)