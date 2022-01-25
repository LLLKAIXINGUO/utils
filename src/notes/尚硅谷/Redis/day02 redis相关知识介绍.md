## day02 redis相关知识介绍

在redis中，默认16个数据库，类似数组下标从0开始，初始默认使用==0号库==

使用命令select <dbid> 来切换数据库 。如  ：select 8

统一密码管理，所有库同样密码

==dbsize==查看当前的数据库的key的数量

==flush 清空当前库==

==flushall 通杀全部库==



Redis是**单线程**+**多路IO复用**技术



#### Redis中常用的五大数据类型

Redis字符串（String）  ：一个redis中字符串的value最多可以是**512M**的

Redis列表（List）

Redis集合（Set）

Redis哈希（Hash）

Redis有序集合Zset



#### Redis键（key）

keys *查看当前库所有的key  (匹配：`key**1`     只用一个星)

判断一个key是否存在   exists key的名称

type key的名称  能查看key的类型

del key的名称，能够删除指定的key

==unlink key 根据value选择非阻塞删除== 仅将keys从keyspace元数据中删除，真正的删除会在后续异步操作

**expire key的名字 10**       10秒钟，为给定的key设置国企时间

 -2表示已经过期了     ，，，，，-1表示永不过期



set  <key>  <value> 命令  向库中加入一条数据

get <key>   取值

append  key的名称  要在后添加的字符

strlen  key的名称  获取该key的字符长度

setnx  <key>  <value>  只有在key不存在时，设置keyd的值

 mset <key1> <value1>  <key2> <value2>  可以同时设置一个或者多个key-value对。k1 

mget  k1 k2 k3    获取多个key

 msetnx <key1> <value1>  <key2> <value2>  可以同时设置一个或者多个key-value对。当且仅当所有给定的key都不存在

==原子性，有一个失败则都失败== 

setex  <key> <过期时间> <value>

getset <key> <value> 以新值换旧值

incr <key> 将key中存储的数字值增1   只能对数字值操作，如果为空，新增值为1

decr <key> 将key中存储的数字值减1   只能对数字值操作，如果为空，新增值为-1

ttl <key>  可以查看过期时间



list结构

lpush/rpush <key> <value1><value2><value2> 从左边、右边插入一个或多个值

lpop/rpop  <key> 从左边/右边吐出一个值。==值在键在，值光键亡==

lrange <key><start><stop> 按照索引下标获得元素（从左到右）

lrange <key> 0 -1  取key的所有值

rpoplpush   <key1><key2> 从<key1>列表右边吐出一个值，插到<key2>列表的左边