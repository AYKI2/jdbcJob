package org.example.dao;

import org.example.config.Util;
import org.example.model.Employee;
import org.example.model.Job;

import java.sql.*;
import java.util.*;

public class EmployeeDaoImpl implements EmployeeDao{
    private Connection connection;
    public EmployeeDaoImpl(){
        this.connection = Util.getConnection();
        System.out.println("Employee successfully connected!");
    }
    public void createEmployee() {
        String query = """
                CREATE TABLE if not exists employee(
                id SERIAL PRIMARY KEY,
                first_name VARCHAR(50) NOT NULL,
                last_name VARCHAR(50) NOT NULL,
                age SMALLINT NOT NULL,
                email VARCHAR(100) UNIQUE NOT NULL,
                job_id INT REFERENCES job(id)
                );
                """;
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println("Successfully created!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void addEmployee(Employee employee) {
        String query = """
                INSERT INTO employee(first_name,last_name,age,email,job_id)
                VALUES(?,?,?,?,?);
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setByte(3, employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setLong(5, employee.getJobId());
            preparedStatement.executeUpdate();
            System.out.println("Successfully added!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void dropTable() {
        String query = "DROP TABLE employee";
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println("Successfully deleted!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void cleanTable() {
        String query = "TRUNCATE employee";
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println("Successfully cleared!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateEmployee(Long id, Employee employee) {
        String query = """
                UPDATE employee
                SET first_name = ?,
                last_name = ?,
                age = ?,
                email = ?,
                job_id = ?
                WHERE id = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setByte(3, employee.getAge());
            preparedStatement.setString(4,employee.getEmail());
            preparedStatement.setInt(5,employee.getJobId());
            preparedStatement.setLong(6,id);
            preparedStatement.executeUpdate();
            System.out.println("Successfully updated!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public List<Employee> getAllEmployees() {
        String query = "SELECT * FROM employee";
        List<Employee> employees = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                employees.add(new Employee(resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age"),
                        resultSet.getString("email"),
                        resultSet.getInt("job_id")));
            }
            resultSet.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return employees;
    }

    public Employee findByEmail(String email) {
        String query = "SELECT * FROM employee WHERE email  = ?";
        Employee employees = new Employee();
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                employees.setId(resultSet.getLong("id"));
                employees.setFirstName(resultSet.getString("first_name"));
                employees.setLastName(resultSet.getString("last_name"));
                employees.setAge(resultSet.getByte("age"));
                employees.setEmail(resultSet.getString("email"));
                employees.setJobId(resultSet.getInt("job_id"));
            }
            resultSet.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return employees;
    }

    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        String query = """
                SELECT * FROM employee as e JOIN job j on e.job_id = j.id WHERE e.id = ?;
                """;
        Map<Employee,Job> map = new LinkedHashMap<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                map.put(new Employee(resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age"),
                        resultSet.getString("email"),
                        resultSet.getInt("job_id")),
                        new Job(resultSet.getLong("id"),
                                resultSet.getString("position"),
                                resultSet.getString("profession"),
                                resultSet.getString("description"),
                                resultSet.getInt("experience")));
                break;
            }
            resultSet.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return map;
    }

    public List<Employee> getEmployeeByPosition(String position) {
        String query = """
                SELECT e.*,j.position FROM employee as e JOIN job j on e.job_id = j.id WHERE j.position = ?;
                """;
        List<Employee> employeeList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,position);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                employeeList.add(new Employee(resultSet.getLong("id"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name"),
                                resultSet.getByte("age"),
                                resultSet.getString("email"),
                                resultSet.getInt("job_id")));
                System.out.println(resultSet.getString("position"));
            }
            resultSet.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return employeeList;
    }
}
