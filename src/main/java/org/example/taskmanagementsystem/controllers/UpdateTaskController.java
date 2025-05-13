package org.example.taskmanagementsystem.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.taskmanagementsystem.dao.TaskDao;
import org.example.taskmanagementsystem.models.Task;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.sql.Date;

@WebServlet(name = "UpdateTaskController" , value = "/updateTask")
public class UpdateTaskController extends HttpServlet {
    private final Logger logger = Logger.getLogger(UpdateTaskController.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        String status = request.getParameter("status");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Date dueDate = Date.valueOf(request.getParameter("dueDate"));
        int userId =Integer.parseInt(request.getParameter("userId"));

        //get and update task status
        try{
            TaskDao taskDao = new TaskDao();
            Task task = taskDao.getTaskById(taskId);

            if(task != null){
                task.setStatus(status);
                task.setUserId(userId);
                task.setTitle(title);
                task.setDescription(description);
                task.setDueDate(dueDate);

                taskDao.updateTask(task); // update task
                request.setAttribute("task",task);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/viewTasks.jsp");
            dispatcher.forward(request, response);
        }catch (SQLException e){
            logger.info("Error updating task"+ e.getMessage());
        }
    }
}
