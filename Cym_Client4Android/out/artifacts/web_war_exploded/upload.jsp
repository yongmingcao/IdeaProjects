<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/4/8
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload</title>
</head>
<body>
<center>
    <form
            action="${pageContext.request.contextPath}/upload.do"
            method="post"
            enctype="multipart/form-data"

            >
        <table>
            <tr>
                <td>Name</td> <td><input type="text" name="Name"></td>
            </tr>
            <tr>
                <td>Gender</td> <td><input type="text" name="Gender"></td>
            </tr>
            <tr>
                <td>请选择一个上传文件</td> <td><input type="file" name="image"></td>
            </tr>
            <tr>
                <td><input type="submit" name="上传"></td><td><input type="reset" name="重置"></td>
            </tr>

        </table>



    </form>
</center>

</body>
</html>
