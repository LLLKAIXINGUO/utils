# day19 第一部分  狂神说javaweb  

##### 配置maven

配置环境变量

M2_HOME                    MAVEN 的bin目录

MAVEN_HOME         maven  bin目录的上一级

path配置     %MAVEN_HOME%\bin 

配置远程仓库      ： 阿里云

	 <mirror> 
		<id>alimaven</id> 
		<mirrorOf>*</mirrorOf> 
		<name>aliyun maven</name> 
		<url>https://maven.aliyun.com/nexus/content/groups/public</url> 	
	</mirror> 

配置本地仓库         ：



清理lastupdate文件 bat

```bat
@echo off

rem 本地LastUpdate文件开始删除
set REPOSITORY_PATH=D:\software\apache-maven-3.6.3-bin\apache-maven-3.6.3\maven-repo
rem 正在搜索...
for /f "delims=" %%i in ('dir /b /s "%REPOSITORY_PATH%\*lastUpdated*"') do (
    del /s /q %%i
)
rem 搜索完毕
pause
```

