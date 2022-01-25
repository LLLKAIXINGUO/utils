# git上误传进入了.idea文件夹，处理方式

### 1.首先创建一个新的文件夹，重新从git上拉取该项目

### 2.进入到新拉取的项目下打开终端执行命令

##### 2.1删除github上所误传的文件  .idea

```
git rm -rf --cached .idea
```



##### 2.2.提交并上传  【上传时以自己的实际分支   这里以 origin master分支举例】

```
git commit -m '忽略误删的.idea文件夹'
```

```
git push -u origin master
```





