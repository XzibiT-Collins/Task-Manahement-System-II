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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</head>
<body>

    <div class="container">
        <div class="row g-3">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        Add a Task
                    </div>
                    <div class="card-body">
                        <form action="<%= request.getContextPath() %>/addTask">
                            <div class="form-floating">
                                <input type="text" class="form-control" id="title" placeholder="Title" name="title">
                                <label for="title">Title</label>
                            </div>
                            <div class="form-floating">
                                <input type="text" class="form-control" id="desc" placeholder="Desc" name="description">
                                <label for="desc">Desc</label>
                            </div>
                            <div class="form-floating">
                                <input type="text" class="form-control" id="status" placeholder="Status" name="status">
                                <label for="status">Status</label>
                            </div>
                            <div class="form-floating">
                                <input type="date" class="form-control" id="date" placeholder="Date" name="dueDate">
                                <label for="date">Date</label>
                            </div>
                            <div class="form-floating">
                                <input type="number" class="form-control" id="uId" placeholder="UserID" name="userId">
                                <label for="uId">UserID</label>
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
<%--                Task List--%>
                <table>
                    <tr>
                        <td>Title</td>
                        <td>Description</td>
                        <td>Status</td>
                        <td>Date</td>
                        <td>User</td>
                    </tr>


                    <tbody>
<%--                    Dynamic data--%>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
