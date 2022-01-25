# day03 springboot    增删改查的实现       遇见狂神说

增删改查

1. 提取公共页面     	

   ```html
   th:fragment="sidebar"        //提取公共页面
   th:replace="~{commons/commons::sidebar(active='list.html') //调用公共页面   commons/commons路径：： 提取公共页面的fragment名字 （）里面是传入的参数，相当于get请求中的？传参
   th:class="${active=='main.html'?'nav-link active':'nav-link'}" th:href="@{/main.html}"     对传递的参数进行判断  如果点击了就是高亮显示，没有则不高亮显示
   ```

2. 点击侧边栏按钮的时候，出现高亮显示

3. 遍历数据        以及添加上操作按钮

3. ```html
   <table class="table table-striped table-sm">
      <thead>
         <tr>
            <th>id</th>
            <th>name</th>
            <th>email</th>
            <th>部门</th>
            <th>性别</th>
            <th>生日</th>
            <th>操作</th>
         </tr>
      </thead>
      <tbody>
         <tr th:each="emp:${employees}">
            <td th:text="${emp.getId()}"></td>
            <td th:text="${emp.getLastName()}"></td>
            <td th:text="${emp.getEmail()}"></td>
            <td th:text="${emp.getDepartment().getDepartmentName()}"></td>
            <td th:text="${emp.getGender()==0?'女':'男'}"></td>
            <td th:text="${#dates.format(emp.getBirth(),'yyyy/MM/dd HH:mm:ss')}"></td>
            <td>
               <button class="btn btn-sm btn-primary">修改</button>
               <button class="btn btn-sm btn-danger">删除</button>
            </td>
         </tr>
   
   
      </tbody>
   </table>
   ```

4. 新增员工功能实现
   1. 添加按钮
   2. 跳到添加页面
   3. 实现添加功能
   4. 返回到查询所有的界面

5. 修改员工信息功能
   1. toupdate  去修改页面（携带该条的参数）
   2. 修改并返回至主页面
   
7. 404错误页面 在templates文件夹下创建error文件夹，放入404.html即可

8.   后端的模板  xadmin

9.  前端  界面，至少自己能够通过前端框架，组合出来一个网站页面

