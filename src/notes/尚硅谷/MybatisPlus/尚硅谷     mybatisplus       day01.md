# 尚硅谷     mybatisplus       day01

官方地址     http://mp.baomidou.com 

Github :　　　　https://github.com/baomidou/mybatis-plus

特性：

* 无侵入
* 依赖少
* 预防sql注入
* 通用CRUD操作
* 支持热加载

MybatisPlus会自动的维护Mybatis以及Mybatis-spring相关的依赖

MybatisPlus会默认使用实体类的类名到数据库中找对应的表  表名与类名不一致的时候，加上注解 @TableName

集成MybatisPlus

1. 创建测试表