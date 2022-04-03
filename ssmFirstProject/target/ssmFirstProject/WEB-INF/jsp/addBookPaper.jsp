<%--
  Created by IntelliJ IDEA.
  User: czy
  Date: 2021/9/3
  Time: 9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style>
        h2{
            color: orange;
            text-align: center;
            font-size: 32px;
        }
        a{
            text-decoration: none;
            color: dodgerblue;
        }
    </style>
    <title>Title</title>
</head>
<body>
<h2>新增书籍</h2>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>输入增加书籍的消息</small>
                </h1>
            </div>
        </div>
    </div>

    <form action="${pageContext.request.contextPath}/book/addBook" method="post">
        <div class="form-group">
            <label for="bookName">书名</label>
            <input type="text" class="form-control" id="bookName" placeholder="请输入书籍名" name="bookName">
        </div>
        <div class="form-group">
            <label for="bookCounts">书的数量</label>
            <input type="text" class="form-control" id="bookCounts" placeholder="请输入书的数量" name="bookCounts">
        </div>
        <div class="form-group">
            <label for="detail">详情</label>
            <input type="text" class="form-control" id="detail" placeholder="请输入书籍详情" name="detail">
        </div>
        <button type="submit" class="btn btn-default">提交</button>
    </form>
</div>
</body>
</html>
