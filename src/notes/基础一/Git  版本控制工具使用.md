### Git  版本控制工具使用

分布式版本控制

Git和SVN的区别

SVN是集中式版本控制系统，版本库是放在中央服务器的，而工作的时候，用的都是自己的电脑，所以首先要从中央服务器得到最新的版本，然后工作，完成工作后，需要把自己做完的活推送到中央服务器，集中式版本控制系统是必须联网才能工作，对网络带宽要求较高

Git是分布式版本控制系统，没有中央服务武器，每个人的电脑就是一个完整的版本库，工作的时候不需要联网了，因为版本都在自己的电脑上，协同的方法是这样的：比如说自己在电脑上改了文件A，其他人也在电脑上改了文件A，这时，你们两之间只需要把各自的修改推送给对方，就可以互相看到对方的修改了





Git Bash : Unix与Linux风格的命令行，使用最多 ，推荐使用

Git CMD：Windows风格的命令行

Git GUI:图形界面的Git,不建议使用，尽量先熟悉命令



Git配置

`git  config   -l`     //git中查看配置命令

`git config --system --list`  //系统中配置的git

`git config --global --list`  //用户自己配置的

**所有的配置文件，其实都是保存在本地。**





git 的必要配置

`git config --global user.name "名称"`  #设置名称

`git config --global user.email 1990079407@qq.com` #设置邮箱



git**的使用理论**

![image-20210908142624065](C:\Users\19900\AppData\Roaming\Typora\typora-user-images\image-20210908142624065.png)

git创建本地仓库的两种方法：

1. 使用     `git init`         初始化命令,执行完成后，文件中就会生成一个.git文件【是一个隐藏文件，打开隐藏文件】
2. 使用远程仓库  克隆 /clone  git  clone 【url】

``git status  查看当前文件夹中的所有文件状态`

``git status 【filename】 查看当前文件夹中的filename文件状态`

`git  add  .`  添加所有文件到暂存区

`git commit -m`  提交暂存区中的内容到本地仓库  -m  提交信息

 

**idea中集成Git**

1. 新建项目，绑定git
   * 创建新项目后，将我们远程的git文件目录拷贝到项目中即可
2. 修改文件，使用IDEA操作git
3. 提交测试

