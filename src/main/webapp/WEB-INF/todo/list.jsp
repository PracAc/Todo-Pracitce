<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../includes/header.jsp"%>

<h1>To Do List</h1>
<a href="/todo/regist" class="btn btn-primary">Regist</a>
<%--todolist 테이블--%>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Title</th>
        <th scope="col">Writer</th>
        <th scope="col">Due Date</th>
        <th scope="col">View</th>
    </tr>
    </thead>
    <tbody>
<c:forEach items="${list}" var="todo">
    <tr>
        <td>${todo.title}</td>
        <td>${todo.writer}</td>
        <td>${todo.dueStr}</td>
        <td><a href="/todo/get?tno=${todo.tno}">View</a></td>
    </tr>
</c:forEach>
    </tbody>
</table>

<%--page 처리--%>
<ul class="pagination">
    <c:if test="${pageInfo.prev}">
        <li class="page-item">
            <a href="/todo/list?page=${pageInfo.start - 1}" class="page-link">Previous</a>
        </li>
    </c:if>

    <c:forEach begin="${pageInfo.start}" end="${pageInfo.end}" var="num">
        <li class="page-item ${pageInfo.page == num ? 'active' : ''}">
            <a class="page-link" href="/todo/list?page=${num}">${num}</a>
        </li>
    </c:forEach>

    <c:if test="${pageInfo.next}">
        <li class="page-item">
            <a href="/todo/list?page=${pageInfo.end + 1}" class="page-link">Next</a>
        </li>
    </c:if>
</ul>
<%@include file="../includes/footer.jsp"%>
