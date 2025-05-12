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

@WebServlet(name = "UpdateTaskStatusController" , value = "/updateTaskStatus")
public class UpdateTaskStatusController extends HttpServlet {
    private final Logger logger = Logger.getLogger(UpdateTaskStatusController.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        String status = request.getParameter("status");

        //get and update task status
        try{
            TaskDao taskDao = new TaskDao();
            Task task = taskDao.getTaskById(taskId);

            if(task != null){
                task.setStatus(status);
                taskDao.updateTask(task); // update task
                request.setAttribute("task",task);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/viewTasks.jsp");
            dispatcher.forward(request, response);
        }catch (SQLException e){
            logger.info("Error in updating task status"+ e.getMessage());
        }
    }
}
