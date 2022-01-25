# day01 ajax&json 遇见狂神说  

## **json:**

 json中的要求和语法格式：

* 对象表示为键值对

* 数据用逗号隔开，最后一条数据不能加

* 花括号保存对象

* 方括号保存数组

  **JSON键值对**是 用来保存JS对象的一种方式，和JS对象的写法也大同小异，键/值对组合中的键名写在前面并用双引号“”包裹，使用冒号：分割，然后紧跟着值

  `{“name”: "dfas "`

  `{"age": "3"}`

  `{"sex":"男"}`

  JSON和JS对象的关系：

  JSON是JS对象的字符串表示法，它使用文本表示一个JS对象的信息，本质是一个字符串

**JSON和JS对象互转**

* 要实现从JSON字符串转换为JS对象，使用JSON.parse()方法：
* `var obj  =  JSON.parse('{"a":"Hello",  "b":"World"}'); //结果是{a: 'Hello' , b:'World'}`
* 要实现从JS对象转换为JSON字符串，使用JSON.stringify()方法：
* `var  json = JSON.stringify{a: 'Hello' , b:'World'}  //结果是 {"a":"Hello",  "b":"World"}`

