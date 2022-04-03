<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <style>
        body{
            background-color: #e9ebec;
        }
        a{
            text-decoration: none;
            color: dodgerblue;
        }
        h3{
            width: 460px;
            height: 80px;
            text-align: center;
            font-size: 40px;
            margin: 100px auto;
            line-height: 80px;
            background-color: lightcyan;
            border-radius: 20px;
        }
    </style>
</head>
<body>
<h3>
    <a href="${pageContext.request.contextPath}/book/allBooks">进入书籍页面</a>
</h3>
</body>
</html>
