<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2024-07-31
  Time: 오전 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>My Page</h1>

    <h2>${cookie.member}</h2>

    <form action="/logout" method="post">
        <button type="submit">LOGOUT</button>
    </form>
</body>
</html>
