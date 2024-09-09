<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/mregist" method="post">
        <label>ID</label>
        <input type="text" name="uid">
        <label>PW</label>
        <input type="password" name="upw">
        <label>Email</label>
        <input type="email" name="email">

        <button type="submit">REGIST</button>
    </form>
    <c:if test="${mno < 0}">
        <span>이미 있는 아이디입니다.</span>
    </c:if>
</body>
</html>
