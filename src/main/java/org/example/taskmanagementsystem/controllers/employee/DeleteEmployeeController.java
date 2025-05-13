package org.example.taskmanagementsystem.controllers.employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.taskmanagementsystem.dao.EmployeeDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet(name = "DeleteEmployeeController", value = "/deleteEmployee")
public class DeleteEmployeeController extends HttpServlet {
    private final Logger logger = Logger.getLogger(DeleteEmployeeController.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));

        if(employeeId > 0){
            try{
                EmployeeDao employeeDao = new EmployeeDao();
                employeeDao.deleteEmployee(employeeId);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/addEmployee");
                dispatcher.forward(request,response);

            }catch (SQLException e){
                logger.info("Error deleting employee: "+ e.getMessage());
            }
        }
    }
}
