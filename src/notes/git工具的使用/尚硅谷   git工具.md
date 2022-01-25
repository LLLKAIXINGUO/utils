# 尚硅谷   git工具

**分布式版本控制系统**      

**版本控制：个人开发过渡到团队协作**

版本控制是一种记录文件内容变化，方便将来查阅特定版本修订情况的系统

可以记录文件修改历史记录，从而能够查看历史版本，方便版本切换



（写代码）工作区 -----------> git  add    ------ >            暂存区  ---------->-git  commit------------->              本地库 --------------push--------->远程库



git代码托管中心       gitlab（局域网）           github（互联网） gitee码云（国内网站）

git的安装    双击直接next安装   （安装在非中文无空格的文件夹下）

git常用命令

* git config--global user.name  用户名         设置用户签名（第一次的时候设置上，不设置上git提交的时候是会报错的）
* git config--global user.email 邮箱         设置用户签名

* **git init**     初始化本地库     出来.git的隐藏目录
* **git  status**  查看状态           查看后 红色即是未追踪的文件
* git add  文件名   添加暂存区    
* git commit -m "日志信息"  文件名     
* git reflog  查看版本信息   精简版
* git  log  查看版本的详细信息



## 分支

 分支的底层是指针的应用

* git branch  分支名        创建分支
* git branch    -v                  查看分支
* git checkout  分支名     切换分支
* git  merge  分支名   把指定的分支合并到当前分支
* 

### **冲突合并**： 两个分支对于同一个位置以及同一个文件



**git团队协作机制**          

  push（本地库推到远程库）推送

clone  (远程库拉到本地)  

（团队内协作）      （团队间协作）

代码托管中心



**github创建远程库**

* github右上角的小加号  new  respository



* 创建远程库别名      **remote** 命令
*  推送本地分支到远程库       **git  push  远程库别名或链接   分支名**
* 拉取远程库到本地库            **git  pull  远程库别名或链接   分支名**



* 克隆会做如下的操作：  
  1. 拉取代码
  2. 初始化本地仓库
  3. 创建别名



### 团队协作：



ssh免密登录      c盘  /用户目录





## idea集成git

让git忽略掉一些文件         XXX.ignore文件



file  ------>settings------> version control ---->Git ---配置git的安装目录

找到git的安装目录 然后选择 Git/bin/git.exe文件  然后点击Test直到在git中看到版本信息即安装成功了

最上面的VCS------>import  into  Version Control-------->Create  Git  Respository表示这个项目就被git托管了

红色文件代表    （项目 未被追踪 ，未添加到暂存区）     绿色文件的代表（添加到了暂存区，没有添加到本地库）     蓝色文件（代表被追踪过，但是又改变了）



看到提交版本       在idea的左下角 有一个version control  里面点开点击log



idea中创建分支   右键项目名称      或者idea的右下角的  git master

idea中合并分支         右下角的

idea中合并分支（处理分支冲突）



码云可以直接复制到guthub上的项目



自建代码托管平台  GitLab

