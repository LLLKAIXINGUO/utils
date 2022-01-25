# 尚硅谷   Redis   day01

Redis 6  学习的主要功能 

### NoSQL

1. **NoSQL数据库简介**
2. **Redis6 概述和安装**
   1. NoSQL不依赖业务逻辑方式存储，而以简单的key-value模式存储，大大的增加了数据库的扩展能力
      * 不遵循SQL标准
      * 不支持ACID
      * 远超于SQL的性能
   2. 适用场景
      1. 对数据的高并发的读写
      2. 海量数据的读写
      3. 对数据高可扩展性的
   3. Redis的安装  
      1. 进入官网Redis.io 下载到redis安装包
      2. 装在Linux中  在Linux系统中安装
         1. 使用xftp将其拖拽到 linux的 新拆功能键的eopt目录下
         2. 使用xshell   使用root用户  进入到opt目录下
         3. 安装Redis要依赖安装于C语言的编译环境     gcc 
         4. 查看是否有这个环境  gcc  --version      如果没有  yum  install gcc
         5. 使用tar命令解压这个redis的压缩包文件     tar -zxvf  redis
         6. cd redis-6.2.6 进入到解压后的redis文件目录下
         7. 使用make命令进行编译
         8. 使用make install 进行安装
         9. 安装完成后  进入到 cd  /usr/local/bin目录下，默认会安装到这个目录下
         10. 点击 ll看是否安装成功
             1. redis-benchmark:性能测试工具
             2. redis-check-aof：修复有问题的AOF文件，rdb和aof在后面讲
             3. redis-check-dump :修复有问题的dump.rdb文件
             4. redis-sentinel:Redis集群使用
             5. **redis-server:Redis服务器启动命令**
             6. **redis-cli:客户端，操作入口**
3. **Redis常用的五大数据类型**
4. **Redis6配置文件详解**
5. **Redis6发布和订阅**
6. **Redis6新数据类型**
7. **Jedis操作Redis6**
8. **SpringBoot整合Redis**
9. **Redis 6 的事务操作**
10. Redis6持久化之RDB
11. Redis6持久化之AOF
12. Redis6的主从复制
13. Redis6集群
14. Redis6应用问题解决
15. Redis6新功能 