<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<%--
  Created by IntelliJ IDEA.
  User: czy
  Date: 2021/9/2
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍展示页面</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style>

        h4{
            color: orange;
            text-align: center;
            font-size: 32px;
            width: 240px;
            height: 40px;
            text-align: center;
            margin: 0px auto;
            line-height: 60px;

        }
        .nav {
            margin: 30px auto;
            height: 45px;
            border-top: 3px solid #ff8500;
            border-bottom: 1px solid #edeef0;
            background-color: #fcfcfc;
            line-height: 45px;
        }
        .nav a {
            /* 因爲a是行内元素是没有高的 要转换成行内块 */
            display: inline-block;
            line-height: 41px;
            font-size: 22px;
            padding: 0 20px;
            text-decoration: none;
            color: orange;
        }
        .nav a:hover{
            background-color: #eee;
            color: #ff8500;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="nav">
            <a href="${pageContext.request.contextPath}/book/addBookPaper">新增书籍</a>
        </div>
        <div class="row clearfix">
                    <h3>书籍列表   显示所有数据</h3>
        </div>

        <div class="row clearfix">
            <div class="col-md-12 column">
               <table class="table table-hover table-striped">
                   <thead>
                   <tr>
                       <th>书籍编号</th>
                       <th>书籍名称</th>
                       <th>书籍数量</th>
                       <th>书籍详情</th>
                   </tr>
                   </thead>
                   <tbody>
                    <c:forEach var="book" items="${books}">
                        <tr>
                            <td>${book.bookID}</td>
                            <td>${book.bookName}</td>
                            <td>${book.bookCounts}</td>
                            <td>${book.detail}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/book/toUpdatePaper?id=${book.bookID}">修改</a> &nbsp;| &nbsp;
                                <a href="${pageContext.request.contextPath}/book/deleteBook/${book.bookID}">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                   </tbody>
               </table>
            </div>


        </div>
    </div>
</body>
</html>
