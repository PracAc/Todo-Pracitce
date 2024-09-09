<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>
<style>
    form > div{
        max-width: 500px;
    }
</style>
<h1>To Do Regist</h1>

<form action="/todo/regist" method="post">
    <div class="mb-3">
        <label class="form-label">Title (제목)</label>
        <input type="text" class="form-control" name="title">
    </div>
    <div class="mb-3">
        <label class="form-label">Writer (작성자)</label>
        <input type="text" class="form-control" name="writer" >
    </div>
    <div class="mb-3">
        <label class="form-label">Due (기간)</label>
        <input type="date" class="form-control" name="due_date" >
    </div>
    <div>
        <label class="form-label">Time (시간)</label>
        <input type="time" class="form-control" name="due_time" >
    </div>

    <button type="submit" class="btn btn-primary">REGIST</button>
</form>

<%@include file="../includes/footer.jsp"%>