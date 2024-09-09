<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../includes/header.jsp"%>

<h1>To Do Get</h1>
${todo}

<div class="card" style="width: 18rem;">
</div>
<div class="card" style="width: 18rem;">
    <form action="/todo/edit" method="post">
        <input type="hidden" name="tno" value="${todo.tno}">
        <div class="card-header">
            <input type="text" name="title" value="${todo.title}">
        </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item"><input type="text" name="writer" value="${todo.writer}"></li>
            <li class="list-group-item"><input type="date" name="due_date" value="${todo.dueStr}"></li>
            <li class="list-group-item"><input type="time" name="due_time" value="${todo.dueTime}"></li>
        </ul>
        <button type="submit" class="btn btn-primary">Modfiy</button>
    </form>
    <form action="/todo/remove" method="post">
        <input type="hidden" name="tno" value="${todo.tno}">
        <button type="submit" class="btn btn-danger">Delete</button>
    </form>
</div>
<%@include file="../includes/footer.jsp"%>
