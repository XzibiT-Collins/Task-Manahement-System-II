package org.example.taskmanagementsystem.controllers.employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.taskmanagementsystem.dao.EmployeeDao;
import org.example.taskmanagementsystem.models.Employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "AddEmployeeController", value = "/addEmployee")
public class AddEmployeeController extends HttpServlet {
    private final Logger logger = Logger.getLogger(AddEmployeeController.class.getName());
    private String errorMessage;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            EmployeeDao employeeDao = new EmployeeDao();
            List<Employee> employeeList = employeeDao.getAllEmployees();

            request.setAttribute("employeeList", employeeList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/employee/addEmployee.jsp");
            dispatcher.forward(request, response);

        }catch (SQLException e){
            logger.info("Error getting employee list: "+ e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");

        if(firstName.isBlank() && lastName.isBlank()){
            errorMessage = "First name and last name are required";
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/errors/error.jsp");
            request.setAttribute("errorMessage",errorMessage);
            dispatcher.forward(request, response);
        }

        try{
            EmployeeDao employeeDao = new EmployeeDao();
            Employee employee = new Employee(firstName,lastName);

            employeeDao.addEmployee(employee);

            response.sendRedirect(request.getContextPath() +"/addEmployee");

        }catch (SQLException e){
            logger.info("Error creating employee: "+ e.getMessage());
            errorMessage = "Error creating employee";
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/errors/error.jsp");
            request.setAttribute("errorMessage",errorMessage);
            dispatcher.forward(request, response);
        }
    }
}
