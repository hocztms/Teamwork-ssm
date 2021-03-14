<%--
  Created by IntelliJ IDEA.
  User: 11075
  Date: 2021/2/25
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>hocztms</h3>

    <h3>文件上传</h3>
    <form action="/user/upload" method="post" enctype="multipart/form-data">
        选择文件：<input type="file" name="upload"/><br/>
        <input type="submit" value="上传文件"/>
    </form>

    <form action="/user/download" name="form3" id = "form3" method="post">
        2     <input type = "submit" value="普通文件下载">
        3 </form>

</body>
</html>
