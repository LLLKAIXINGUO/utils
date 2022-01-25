# day04、Nacos注册中心

### Nacos是阿里巴巴的产品， 比Eureka功能更加丰富，在国内受欢迎程度较高



# Nacos安装指南



# 1.Windows安装

开发阶段采用单机安装即可。

## 1.1.下载安装包

在Nacos的GitHub页面，提供有下载链接，可以下载编译好的Nacos服务端或者源代码：

GitHub主页：https://github.com/alibaba/nacos

GitHub的Release下载页：https://github.com/alibaba/nacos/releases

如图：

![image-20210402161102887](G:%5CTYPORA%E6%96%87%E6%A1%A3%E5%A1%AB%E5%85%85%5C%E9%BB%91%E9%A9%ACspringcloud%E5%86%85%E5%AE%B9%5Cspringcloud%5Cday01%20model4%20Nacos%E6%B3%A8%E5%86%8C%E4%B8%AD%E5%BF%83.assets%5Cimage-20210402161102887.png)



本课程采用1.4.1.版本的Nacos，课前资料已经准备了安装包：

![image-20210402161130261](G:%5CTYPORA%E6%96%87%E6%A1%A3%E5%A1%AB%E5%85%85%5C%E9%BB%91%E9%A9%ACspringcloud%E5%86%85%E5%AE%B9%5Cspringcloud%5Cday01%20model4%20Nacos%E6%B3%A8%E5%86%8C%E4%B8%AD%E5%BF%83.assets%5Cimage-20210402161130261.png)

windows版本使用`nacos-server-1.4.1.zip`包即可。



## 1.2.解压

将这个包解压到任意非中文目录下，如图：

![image-20210402161843337](G:%5CTYPORA%E6%96%87%E6%A1%A3%E5%A1%AB%E5%85%85%5C%E9%BB%91%E9%A9%ACspringcloud%E5%86%85%E5%AE%B9%5Cspringcloud%5Cday01%20model4%20Nacos%E6%B3%A8%E5%86%8C%E4%B8%AD%E5%BF%83.assets%5Cimage-20210402161843337.png)

目录说明：

- bin：启动脚本
- conf：配置文件



## 1.3.端口配置

Nacos的默认端口是8848，如果你电脑上的其它进程占用了8848端口，请先尝试关闭该进程。

**如果无法关闭占用8848端口的进程**，也可以进入nacos的conf目录，修改配置文件中的端口：

![image-20210402162008280](G:%5CTYPORA%E6%96%87%E6%A1%A3%E5%A1%AB%E5%85%85%5C%E9%BB%91%E9%A9%ACspringcloud%E5%86%85%E5%AE%B9%5Cspringcloud%5Cday01%20model4%20Nacos%E6%B3%A8%E5%86%8C%E4%B8%AD%E5%BF%83.assets%5Cimage-20210402162008280.png)

修改其中的内容：

![image-20210402162251093](G:%5CTYPORA%E6%96%87%E6%A1%A3%E5%A1%AB%E5%85%85%5C%E9%BB%91%E9%A9%ACspringcloud%E5%86%85%E5%AE%B9%5Cspringcloud%5Cday01%20model4%20Nacos%E6%B3%A8%E5%86%8C%E4%B8%AD%E5%BF%83.assets%5Cimage-20210402162251093.png)



## 1.4.启动

启动非常简单，进入bin目录，结构如下：

![image-20210402162350977](G:%5CTYPORA%E6%96%87%E6%A1%A3%E5%A1%AB%E5%85%85%5C%E9%BB%91%E9%A9%ACspringcloud%E5%86%85%E5%AE%B9%5Cspringcloud%5Cday01%20model4%20Nacos%E6%B3%A8%E5%86%8C%E4%B8%AD%E5%BF%83.assets%5Cimage-20210402162350977.png)

然后执行命令即可：

- windows命令：

  ```bash
  startup.cmd -m standalone
  ```


执行后的效果如图：

![image-20210402162526774](G:%5CTYPORA%E6%96%87%E6%A1%A3%E5%A1%AB%E5%85%85%5C%E9%BB%91%E9%A9%ACspringcloud%E5%86%85%E5%AE%B9%5Cspringcloud%5Cday01%20model4%20Nacos%E6%B3%A8%E5%86%8C%E4%B8%AD%E5%BF%83.assets%5Cimage-20210402162526774.png)



## 1.5.访问

在浏览器输入地址：http://127.0.0.1:8848/nacos即可：

![image-20210402162630427](G:%5CTYPORA%E6%96%87%E6%A1%A3%E5%A1%AB%E5%85%85%5C%E9%BB%91%E9%A9%ACspringcloud%E5%86%85%E5%AE%B9%5Cspringcloud%5Cday01%20model4%20Nacos%E6%B3%A8%E5%86%8C%E4%B8%AD%E5%BF%83.assets%5Cimage-20210402162630427.png)

默认的账号和密码都是nacos，进入后：

![image-20210402162709515](G:%5CTYPORA%E6%96%87%E6%A1%A3%E5%A1%AB%E5%85%85%5C%E9%BB%91%E9%A9%ACspringcloud%E5%86%85%E5%AE%B9%5Cspringcloud%5Cday01%20model4%20Nacos%E6%B3%A8%E5%86%8C%E4%B8%AD%E5%BF%83.assets%5Cimage-20210402162709515.png)





# 2.Linux安装

Linux或者Mac安装方式与Windows类似。

## 2.1.安装JDK

Nacos依赖于JDK运行，索引Linux上也需要安装JDK才行。

上传jdk安装包：

![image-20210402172334810](G:%5CTYPORA%E6%96%87%E6%A1%A3%E5%A1%AB%E5%85%85%5C%E9%BB%91%E9%A9%ACspringcloud%E5%86%85%E5%AE%B9%5Cspringcloud%5Cday01%20model4%20Nacos%E6%B3%A8%E5%86%8C%E4%B8%AD%E5%BF%83.assets%5Cimage-20210402172334810.png)

上传到某个目录，例如：`/usr/local/`



然后解压缩：

```sh
tar -xvf jdk-8u144-linux-x64.tar.gz
```

然后重命名为java



配置环境变量：

```sh
export JAVA_HOME=/usr/local/java
export PATH=$PATH:$JAVA_HOME/bin
```

设置环境变量：

```sh
source /etc/profile
```





## 2.2.上传安装包

如图：

![image-20210402161102887](G:/%25E9%25BB%2591%25E9%25A9%25ACspringcloud%25E8%25AF%25BE%25E7%25A8%258B%25E8%25B5%2584%25E6%25BA%2590/%25E9%25BB%2591%25E9%25A9%25ACspringcloud/%25E5%259F%25BA%25E7%25A1%2580%25E7%25AF%2587/%25E8%25AE%25B2%25E4%25B9%2589/day01-SpringCloud01/%25E8%25B5%2584%25E6%2596%2599/assets/image-20210402161102887.png)

也可以直接使用课前资料中的tar.gz：

![image-20210402161130261](G:/%25E9%25BB%2591%25E9%25A9%25ACspringcloud%25E8%25AF%25BE%25E7%25A8%258B%25E8%25B5%2584%25E6%25BA%2590/%25E9%25BB%2591%25E9%25A9%25ACspringcloud/%25E5%259F%25BA%25E7%25A1%2580%25E7%25AF%2587/%25E8%25AE%25B2%25E4%25B9%2589/day01-SpringCloud01/%25E8%25B5%2584%25E6%2596%2599/assets/image-20210402161130261.png)

上传到Linux服务器的某个目录，例如`/usr/local/src`目录下：

![image-20210402163715580](G:%5CTYPORA%E6%96%87%E6%A1%A3%E5%A1%AB%E5%85%85%5C%E9%BB%91%E9%A9%ACspringcloud%E5%86%85%E5%AE%B9%5Cspringcloud%5Cday01%20model4%20Nacos%E6%B3%A8%E5%86%8C%E4%B8%AD%E5%BF%83.assets%5Cimage-20210402163715580.png)



## 2.3.解压

命令解压缩安装包：

```sh
tar -xvf nacos-server-1.4.1.tar.gz
```

然后删除安装包：

```sh
rm -rf nacos-server-1.4.1.tar.gz
```

目录中最终样式：

![image-20210402163858429](G:%5CTYPORA%E6%96%87%E6%A1%A3%E5%A1%AB%E5%85%85%5C%E9%BB%91%E9%A9%ACspringcloud%E5%86%85%E5%AE%B9%5Cspringcloud%5Cday01%20model4%20Nacos%E6%B3%A8%E5%86%8C%E4%B8%AD%E5%BF%83.assets%5Cimage-20210402163858429.png)

目录内部：

![image-20210402164414827](G:%5CTYPORA%E6%96%87%E6%A1%A3%E5%A1%AB%E5%85%85%5C%E9%BB%91%E9%A9%ACspringcloud%E5%86%85%E5%AE%B9%5Cspringcloud%5Cday01%20model4%20Nacos%E6%B3%A8%E5%86%8C%E4%B8%AD%E5%BF%83.assets%5Cimage-20210402164414827.png)



## 2.4.端口配置

与windows中类似



## 2.5.启动

在nacos/bin目录中，输入命令启动Nacos：

```sh
sh startup.sh -m standalone
```







# 3.Nacos的依赖

父工程：

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-alibaba-dependencies</artifactId>
    <version>2.2.5.RELEASE</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>
```



客户端：

```xml
<!-- nacos客户端依赖包 -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>

```





## Nacos注册中心

#### 服务注册到Nacos

1. 在cloud-demo父工程中添加spring-cloud-alibaba的管理依赖：

```xml
<!-- springCloudAlibaba   nacos的管理依赖 ，用来管理版本的-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-alibaba-dependencies</artifactId>
    <version>2.2.5.RELEASE</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>
```

2. 在服务中注释掉eureka的客户端依赖

3. 导入nacos客户端依赖

   ```xml
   <dependency>
       <groupId>com.alibaba.cloud</groupId>
      <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
   </dependency>
   
   ```

4. 在yaml文件中修改配置

   * 在application.yaml中注释掉eureka的配置
   * 在spring的application同级下配置springcloudalibaba的配置

### 1.nacos服务分级存储模型

1. 一级是服务，例如 userservice
2. 二级是集群，例如杭州或上海
3. 三级是实例，例如杭州机房的某台部署了userservice的服务器。

### 2.如何设置实例的集群属性

1. 修改application.yaml文件，添加  spring.cloud.nacos.discovery.cluster-name属性即可

   ```yaml
   例如:
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/cloud_user?useSSL=false
       username: root
       password: 1234
       driver-class-name: com.mysql.jdbc.Driver
     application:
       name: userservice  #user的服务名称
     cloud:
       nacos:
         server-addr: localhost:8848   #nacos服务地址
         discovery:
           cluster-name: HZ       #集群名称 配置成杭州集群
           
           ===============》
            discovery:
           cluster-name: HZ       #集群名称 配置成杭州集群
   ```

   ##### 根据权重负载均衡
   
2. 在user-service配置成有两个地区的集群后，在order-service中也配置集群地址     此时查询还是轮询所有。

3. 还需要再配置上轮询规则

###### 实际部署中会出现这样的场景：

* 服务器设备性能有差异，部分实例所在的机器性能比较好，另一些较差，我们希望性能好的 机器承担更多的用户请求

  Nacos提供了权重配置来控制访问频率



## 2.5.启动

在nacos/bin目录中，输入命令启动Nacos：

```sh
sh startup.sh -m standalone
```







# 3.Nacos的依赖

父工程：spring-cloud-alibaba的管理依赖     在父工程中添加这个依赖

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-alibaba-dependencies</artifactId>
    <version>2.2.5.RELEASE</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>
```



客户端：        在服务端注掉eureka的客户端依赖，添加进去nacos的客户端依赖

```xml
<!-- nacos客户端依赖包 -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>

```





## 环境隔离--namespace

Nacos中服务存储和数据存储的最外层都是一个名为namespace的东西，用来做最外层隔离。 

1. 用namespace来做环境隔离
2. 每个namespace都有唯一id
3. 不同的namespace下的服务不可见



#### Nacos和eureka的共同点

* 都支持服务注册和服务拉取
* 都支持服务提供者心跳方式做健康检测’

#### Nacos和Eureka的区别

* Nacos支持服务端主动检测提供者状态：临时实例采用心跳模式，非临时实例采用主动检测模式
* 临时实例心跳不正常会被剔除，非临时实例则不会被剔除
* Nacos支持服务列表变更的消息推送模式，服务列表更新更及时
* Nacos集群默认采用AP方式，当集群中存在非临时实例时，采用CP模式，Eureka采用AP方式。