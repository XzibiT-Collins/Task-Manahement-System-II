package org.example.taskmanagementsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.example.taskmanagementsystem.db.DatabaseConnector;
import org.example.taskmanagementsystem.models.Task;

public class TaskDao {
    Logger logger = Logger.getLogger(TaskDao.class.getName());

    private final Connection connection;
    public TaskDao() throws SQLException {
        this.connection = DatabaseConnector.getConnection();
    }

    //get Task by ID
    public Task getTaskById(int id) throws SQLException {
        String query = "SELECT * FROM task WHERE id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Task task = new Task(
                        resultSet.getInt("user_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("status"),
                        resultSet.getDate("dueDate")
                );
                task.setId(resultSet.getInt("id")); //set id on a task
                return task;
            }
        }catch (SQLException e){
            throw new SQLException();
        }
        return null;
    }

    public void addTask(Task task)throws SQLException{
        System.out.println("Adding task");
        String query = "INSERT INTO task(title,description,status,due_date,user_id) VALUES(?,?,?,?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,task.getTitle());
            preparedStatement.setString(2,task.getDescription());
            preparedStatement.setString(3,task.getStatus());
            preparedStatement.setDate(4, task.getDueDate());
            preparedStatement.setInt(5,task.getUserId());

            //create the task
            preparedStatement.executeUpdate();

            logger.info("Task created successfully");
        }catch (SQLException e){
            logger.info("Error creating task: "+ e.getMessage());
            throw new SQLException();
        }
    }
}
