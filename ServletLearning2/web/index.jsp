<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/4/10
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <a href="testOne.do?userName=曹永明&psw=123548">连接到第一个Servlet</a>
ServletLearning2
  <hr/>
  <form action="testOne.do" method="post">
      用户姓名：<input type="text" name="userName">
      <br/>
      用户密码：<input type="text" name="userPsw">
      <br/>
    <input type="submit" value="提交">
  </form>
  </body>
</html>
