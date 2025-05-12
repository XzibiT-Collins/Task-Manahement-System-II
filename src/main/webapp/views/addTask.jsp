<%--
  Created by IntelliJ IDEA.
  User: XzibiT
  Date: 5/11/2025
  Time: 7:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create a Task</title>
</head>
<body>
    <h1>Add a Task to begin tracking</h1>
    <form action="<%= request.getContextPath() %>/addTask" method="post">
        <label for="title">Title</label>
        <input type="text" name="title" id="title">
        <label for="description">Desc</label>
        <input type="text" name="description" id="description">
        <label for="status">Status</label>
        <input type="text" name="" id="status">
        <label for="dueDate">Date</label>
        <input type="date" name="dueDate" id="dueDate">
        <label for="userId">UserID</label>
        <input type="number" id="userId" name="userId">
        <input type="submit">
    </form>
</body>
</html>
