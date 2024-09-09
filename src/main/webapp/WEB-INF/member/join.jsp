<%--
  Created by IntelliJ IDEA.
  User: cooki
  Date: 2024-08-01
  Time: 오전 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<script>
    function move(){
        window.location="/login"
    }
</script>


<form action="/member/join" method="post"  target='zero' >
    <div>
        <input type="text" name="uid" value="aaa">
    </div>
    <div>
        <input type="text" name="email" value="aaa@bbb.com">
    </div>
    <div>
        <input type="password" name="upw" value="1111">
    </div>
    <button>JOIN</button>
</form>


<iframe name="zero" style="border: 1px"></iframe>

</body>
</html>