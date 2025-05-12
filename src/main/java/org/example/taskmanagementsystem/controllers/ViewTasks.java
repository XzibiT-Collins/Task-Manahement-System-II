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

@WebServlet("/viewTask")
public class ViewTasks extends HttpServlet {
    private final Logger logger = Logger.getLogger(ViewTasks.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));

        try{
            TaskDao taskDao = new TaskDao();
            Task task = taskDao.getTaskById(taskId);

            request.setAttribute("task",task);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/templates/viewTasks.jsp");
            requestDispatcher.forward(request,response);
        }catch (SQLException e){
            logger.info("Error getting task: "+ e.getMessage());
        }
    }
}
