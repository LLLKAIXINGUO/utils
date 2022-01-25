# day11  第一部分  bootstrap响应式布局

Bootstrap  ：   **一个前端开发的框架**



#### **使用bootstrap** 

1. 下载bootstrap到本地进行解压
2. 在项目中引入bootstrap资源【三个文件夹】
3. 创建html页面， 引入必要的资源

```html
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css" >
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="/resources/js/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="js/bootstrap.min.js"></script>

</head>
<body>
<h1>你好，世界！</h1>


</body>
</html>
```

### 响应式布局

* 同一套页面，可以兼容不同分辨率的设备
* 实现：依赖于栅格系统【bootstrap中】将一行均分为12个格子，可以指定元素占几个格子
* 步骤
  1. 定义容器，相当于之前的table
     * 容器分类：
       1. container:   两边有留白
       2. container-fluid:       每一种设备都是百分百宽度
  2. 定义行，相当于之前的tr  样式：row
  3. 定义元素。指定该元素在不同的设备上，所占的格子数目.样式：  col-设备代号-格子数目
     * 设备代号
       1. xs:超小屏幕      手机（<768px）   eg :col-xs-12
       2. sm:小屏幕        平板（>=768px）
       3. md:中等屏幕  桌面显示器  （>=992px）
       4. lg:大屏幕   大桌面显示器  （>=1200px）

注意事项：

1. 一行中格子数目超过12  则会自动换行
2. 栅格类向上兼容      因为是大于等于    xs变大都还是那样
3. 如果设备的宽度小于设置栅格类属性的设备代码的最小值，会一个元素占满一整行



**全局样式：** [可以通过查看文档去做]

* 按钮                class="btn btn-danger"  按钮不同颜色

  ```html
  <button class="btn btn-danger">fdfdsdf</button>
  <button class="btn btn-primary">fsdfsda</button>
  <button class="btn-default">sfdafsd</button>
  <button class="btn btn-info">sdffdfd</button>
  <button class="btn btn-link">sdaffdas</button>
  ```

* 图片  ：          class="img-responsive"  图片在任意尺寸占百分之百【宽度】  图片的形状        class="img-responsive img=" 

  ```html
  <img src="/resources/微信图片_20211001142035.jpg" class="img-responsive">
  <img src="/resources/微信图片_20211001142035.jpg" class="img-responsive img-circle">
  <img src="/resources/微信图片_20211001142035.jpg" class="img-responsive img-rounded">
  <img src="/resources/微信图片_20211001142035.jpg" class="img-responsive img-thumbnail">
  ```

* 表格

* 表单

**组件：**

* 导航条

  ```html
  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">Brand</a>
      </div>
  
      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
          <li><a href="#">Link</a></li>
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="#">Action</a></li>
              <li><a href="#">Another action</a></li>
              <li><a href="#">Something else here</a></li>
              <li role="separator" class="divider"></li>
              <li><a href="#">Separated link</a></li>
              <li role="separator" class="divider"></li>
              <li><a href="#">One more separated link</a></li>
            </ul>
          </li>
        </ul>
        <form class="navbar-form navbar-left">
          <div class="form-group">
            <input type="text" class="form-control" placeholder="Search">
          </div>
          <button type="submit" class="btn btn-default">Submit</button>
        </form>
        <ul class="nav navbar-nav navbar-right">
          <li><a href="#">Link</a></li>
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="#">Action</a></li>
              <li><a href="#">Another action</a></li>
              <li><a href="#">Something else here</a></li>
              <li role="separator" class="divider"></li>
              <li><a href="#">Separated link</a></li>
            </ul>
          </li>
        </ul>
      </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
  </nav>
  
  
  
  <nav class="navbar navbar-default navbar-inverse">//修改为指责一句，导航栏变成黑色的
  ```

* 分页条    

  ```html
  <nav aria-label="Page navigation">
    <ul class="pagination">
      <li>
        <a href="#" aria-label="Previous">
          <span aria-hidden="true">&laquo;</span>
        </a>
      </li>
      <li><a href="#">1</a></li>
      <li><a href="#">2</a></li>
      <li><a href="#">3</a></li>
      <li><a href="#">4</a></li>
      <li><a href="#">5</a></li>
      <li>
        <a href="#" aria-label="Next">
          <span aria-hidden="true">&raquo;</span>
        </a>
      </li>
    </ul>
  </nav>
  ```

* ```html
  <nav aria-label="...">
    <ul class="pagination">
      <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
      <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
      ...
    </ul>
  </nav>
  //分页
  ```

* 轮播图

* ```html
  <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
      <li data-target="#carousel-example-generic" data-slide-to="1"></li>
      <li data-target="#carousel-example-generic" data-slide-to="2"></li>
    </ol>
  
    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      <div class="item active">
        <img src="..." alt="...">
        <div class="carousel-caption">
          ...
        </div>
      </div>
      <div class="item">
        <img src="..." alt="...">
        <div class="carousel-caption">
          ...
        </div>
      </div>
      ...
    </div>
  
    <!-- Controls -->
    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
  //轮播图
  ```

按钮调整背景色：  

```html
按钮  eg：<button class="btn btn-danger">fdfdsdf</button>
有以下几种   首选项、成功、一般信息、警告、危险、链接
```



