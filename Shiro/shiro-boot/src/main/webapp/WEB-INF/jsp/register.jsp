<%--
  Created by IntelliJ IDEA.
  User: czy
  Date: 2021/11/2
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%--解决页面乱码--%>
<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>register</title>
</head>

<body>
<h3>用户注册</h3>

<form action="${pageContext.request.contextPath}/user/register" method="post">
    用户名:<input type="text" name="username"> <br/>
    密码 : <input type="text" name="password"> <br>
    验证码: <input type="text" name="verifyCode"><img src="${pageContext.request.contextPath}/user/getImage" alt=""><br>
    <input type="submit" value="立即注册">
</form>
</body>
</html>