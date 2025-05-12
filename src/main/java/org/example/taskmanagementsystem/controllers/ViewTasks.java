package org.example.taskmanagementsystem.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/tasks")
public class ViewTasks extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("templates/views/viewTasks.jsp");
        requestDispatcher.forward(request,response);
    }
}
