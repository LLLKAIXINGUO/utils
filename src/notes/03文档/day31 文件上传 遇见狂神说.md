

#  day31 文件上传 遇见狂神说

1. 文件上传对前端表单的要求：为了能上传文件，必须将表单的method设置为post,并将enctype设置为multipart/form-data     只有这样的情况下，浏览器才会把用户选择的文件以二进制流数据发送给服务器



1. 导入文件上传的包

   ```xml
   <!--        文件上传-->
           <dependency>
               <groupId>commons-fileupload</groupId>
               <artifactId>commons-fileupload</artifactId>
               <version>1.3.3</version>
           </dependency>
   <!--        servlet-api导入高版本的-->
           <dependency>
               <groupId>javax.servlet</groupId>
               <artifactId>javax.servlet-api</artifactId>
               <version>4.0.1</version>
           </dependency>
   ```



2. 写入html文件 

```html
<form action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data" method="post">
  <input type="file" name="file"/>
  <input type="submit" value="upload"/>
</form>
```

3. 写controller类

   ```java
   @RestController
   public class FileController {
       @RequestMapping("/upload")
       public String uploadFile(){
           return null;
       }
   }
   ```

4. 写入文件上传的所使用的固定的springmvc的配置

```xml
<!--    文件上传配置-->
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
<!--        请求的编码格式，必须和jsp的pageEncoding属性一致，以便正确读取表单中的内容，默认为”“iso-8859-1-->
        <property name="defaultEncoding" value="utf-8"/>
<!--        上传文件的大小，单位为字节10485760=10M-->
        <property name="maxUploadSize" value="10485760"/>
        <property name="maxInMemorySize" value="40960"/>
    </bean>
```

```
java.lang.ClassNotFoundException: org.apache.commons.fileupload.FileItemFactory
```

**ClassNotFoundException**        ：说明导入进去的包没有被发布