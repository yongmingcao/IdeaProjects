<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <meta charset="utf-8">
    <title>静态加载图片</title>
  </head>
  <body>
  <%--<img alt="" src="${pageContext.request.contextPath}/images/p1.jpg">--%>
   <%--get请求--%>
  <%--<img src="${pageContext.request.contextPath}/getImage.jpeg" alt="">--%>
  <img src="${pageContext.request.contextPath}/getImage.do?id=1" height="108px" width="192px">
  <hr>
  <img src="${pageContext.request.contextPath}/getImage.do?id=2" height="108px" width="192px">


  </body>
</html>