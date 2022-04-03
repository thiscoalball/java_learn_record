<%--
  Created by IntelliJ IDEA.
  User: czy
  Date: 2021/9/3
  Time: 10:21
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
<h2>修改书籍</h2>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>输入修改书籍的消息</small>
                </h1>
            </div>
        </div>
    </div>

    <form action="${pageContext.request.contextPath}/book/updateBook" method="post">
<%--        因为是按照id修改 所以还要用隐藏域传递一个bookId--%>
        <input type="hidden" name="bookID" value="${books.bookID}">
        <div class="form-group">
            <label for="bookName">书名</label>
            <input type="text" class="form-control" id="bookName" placeholder="请输入书籍名" name="bookName" value="${books.bookName}">
        </div>
        <div class="form-group">
            <label for="bookCounts">书的数量</label>
            <input type="text" class="form-control" id="bookCounts" placeholder="请输入书的数量" name="bookCounts" value="${books.bookCounts}">
        </div>
        <div class="form-group">
            <label for="detail">详情</label>
            <input type="text" class="form-control" id="detail" placeholder="请输入书籍详情" name="detail" value="${books.detail}">
        </div>
        <button type="submit" class="btn btn-default">修改</button>
    </form>
</div>
</body>
</html>
