<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../includes/header.jsp"%>

<h1>To Do Get</h1>
${todo}

<div class="card" style="width: 18rem;">
</div>
<div class="card" style="width: 18rem;">
    <div class="card-header">
        <h5 class="card-title">${todo.title}</h5>
    </div>
    <ul class="list-group list-group-flush">
        <li class="list-group-item">${todo.writer}</li>
        <li class="list-group-item">${todo.dueStr}</li>
        <li class="list-group-item">${todo.dueTime}</li>
    </ul>
    <div class="card-body">
        <a href="/todo/edit?tno=${todo.tno}" class="card-link">Modfiy/Delete</a>
    </div>
</div>
<%@include file="../includes/footer.jsp"%>
