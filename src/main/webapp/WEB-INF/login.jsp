<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2024-07-31
  Time: 오전 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/login" method="post">
        <div>
            <img src="/capcha">
            <input name="num" type="text">
        </div>
        <input name="uid" type="text">
        <input name="upw" type="password">
        <button>LOGIN</button>
    </form>
    <a href="/mregist">REGIST</a>
</body>
</html>
