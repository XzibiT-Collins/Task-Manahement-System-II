package org.example.taskmanagementsystem.controllers;

import org.example.taskmanagementsystem.dao.TaskDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.taskmanagementsystem.models.Task;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet("/addTask")
public class AddTaskController extends HttpServlet{
    private final Logger logger = Logger.getLogger(AddTaskController.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/views/addTask.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        //call dao to create a new task
        int userId = Integer.parseInt(request.getParameter("userId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        Date dueDate = Date.valueOf(request.getParameter("dueDate"));

        try{
//          Task task = new Task(userId,title,description,status,dueDate);
            Task task = new Task(1,"Test","Test","Test",null);

            //Dao to handle task creation
            TaskDao taskDao = new TaskDao();

            taskDao.addTask(task); //creates the task in db

            response.sendRedirect(request.getContextPath() +"/tasks");
        }catch (SQLException e){
            logger.info("Error creating task: "+ e.getMessage());
        }
    }
}
