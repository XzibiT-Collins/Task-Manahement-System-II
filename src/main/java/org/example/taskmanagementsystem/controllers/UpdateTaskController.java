package org.example.taskmanagementsystem.controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import org.example.taskmanagementsystem.dao.TaskDao;
import org.example.taskmanagementsystem.models.Task;

@WebServlet(name = "UpdateTaskController",value = "/updateTask")
public class UpdateTaskController extends HttpServlet {
    private final Logger logger = Logger.getLogger(UpdateTaskController.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        Date dueDate = Date.valueOf(request.getParameter("dueDate"));
        int userId = request.getIntHeader("userId");

        try{
            TaskDao taskDao = new TaskDao();
            Task task = new Task(taskId, userId, title, description, status, dueDate);
            taskDao.updateTask(task);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/viewTask");
            dispatcher.forward(request,response);

        }catch (SQLException e){
            logger.info("Error updating task: "+ e.getMessage());
        }
    }
}
