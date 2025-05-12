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
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "AddTaskController", value = "/addTask")
public class AddTaskController extends HttpServlet{
    private final Logger logger = Logger.getLogger(AddTaskController.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            TaskDao taskDao = new TaskDao();
            List<Task> taskList = taskDao.getAllTasks(); // get all Tasks

            System.out.println(taskList);
            // Add taskList to request scope
            request.setAttribute("taskList", taskList);

            // Forward to JSP
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/templates/addTask.jsp");
            requestDispatcher.forward(request, response);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
            out.println("<html><body>");
            out.println("<h1>An Error occurred</h1>");
            out.println("</body></html>");
        }
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
            TaskDao taskDao = new TaskDao();
            //create a task object
            Task task = new Task(userId,title,description,status,dueDate);
            taskDao.addTask(task); //creates the task in db

            response.sendRedirect(request.getContextPath() +"/addTask"); // redirect user
        }catch (SQLException e){
            logger.info("Error creating task: "+ e.getMessage());
        }
    }
}
