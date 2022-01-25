# Day01 Redis 安装

No SQL          NOT  ONLY SQL 不仅仅是sql  ， 不支持ACID,元超于sql

#### NOSQL的使用场景

* 对数据高并发的读写
* 海量数据的读写
* 对数据高扩展性的

#### NOSQL不适用的场景

* 需要事务支持
* 基于sql的结构话查询存储，处理复杂的关系，需要==即席==查询。
* ==（用不着sql的和使用了sql也不行的情况，请考虑用Nosql）==



###### Redis除了支持持久化，主要用作备份恢复

除了支持简单的key-value模式，还==支持多种数据结构的存储==，比如==list、set、hash、zset等。

一般是作为==缓存数据库==辅助持久化的数据库



## Redis的安装

1. 进入Redis的官网     http:redis.io          点击redis的最新的稳定版即是下载了

2. redis安装需要有最新版的gcc 编译器 安装c语言的编译环境

   ```bash
   yum install gcc
   ```

   测试gcc版本

   ```bash
   gcc --version
   ```

   

3. 使用xftp工具，将redis的压缩包放入到  虚拟机的opt目录下



4. 解压redis的压缩包。

   解压命令： 

   ```bash
   tar -zxvf redis-版本号.tar.gz
   ```

5. 在redis解压后的目录下再次执行make命令（只是编译好）==如果没有准备好c语言编译环境，make会报错  -Jemalloc/jemalloc.h:没有那个文件
6. 进入到redis的解压目录后，使用make命令编译，然后使用make install命令安装

7. 安装目录：    在redis下直接进入到   cd  /usr/local/bin 目录下  ll展示有：

查看默认安装目录

redis-benchmark        ：性能测试工具，可以在自己本子运行，看看自己本子性能如何

redis-check-aof            ：  修复有问题的AOF文件，

redis-server                  ： redis服务器启动命令

redis-cli               :               客户端，操作入口

redis-sentinel            :        redis集群使用菜单

8. redis的启动方式

   1. 前台启动：redis-server       ctrl+c停止

   2. 后台启动：（推荐）

      1. cd /opt
      2. cd redis的解压文件夹下   cp redis.conf /etc/redis.conf    将配置文件复制到/etc目录下
      3. 进入到redis.conf 设置daemonize 的 no 改为yes 【表示支持后台启动】

      4. cd /usr/local/bin 
      5. 启动redis命令redis-server /etc/redis.conf
      6. 通过  ps -ef | grep redis  查看redis的进程
      7. 进入到redis的解压目录redis-cli进入到redis的连接客户端 ping一下出现pong表示此时是一个正常的连通状态
   
9. redis的关闭方式

   1. 单实例关闭   ：      redis-cli shutdown
   2. 也可以进入终端后关闭    127.0.0.1：6379>输入ping的位置处输入shutdown命令即可
   3. 多实例端口关闭