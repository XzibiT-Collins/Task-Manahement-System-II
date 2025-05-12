package org.example.taskmanagementsystem.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.taskmanagementsystem.dao.TaskDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet(name = "DeleteTaskController", value = "/deleteTask")
public class DeleteTaskController extends HttpServlet {
    private final Logger logger = Logger.getLogger(DeleteTaskController.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int taskId = Integer.parseInt(request.getParameter("taskId"));

        try{
            TaskDao taskDao = new TaskDao();
            taskDao.deleteTask(taskId);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/addTask");
            dispatcher.forward(request,response);

        }catch (SQLException e){
            logger.info("Error deleting task: "+ e.getMessage());
        }
    }
}
