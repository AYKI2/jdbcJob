package org.example.dao;

import org.example.config.Util;
import org.example.model.Employee;
import org.example.model.Job;
import org.example.service.JobService;
import org.example.service.JobServiceImpl;
import org.postgresql.jdbc.PgResultSet;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JobDaoImpl implements JobDao{
    private Connection connection;

    public JobDaoImpl() {
        this.connection = Util.getConnection();
        System.out.println("Job successfully connected!");
    }

    public void createJobTable() {
        String query = """
                CREATE TABLE if not exists job(
                id SERIAL PRIMARY KEY,
                position VARCHAR NOT NULL,
                profession VARCHAR NOT NULL,
                description VARCHAR NOT NULL,
                experience INT NOT NULL
                );
                """;
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println("Successfully created!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void addJob(Job job) {
        String query = """
                INSERT INTO job(position,profession,description,experience)
                VALUES(?,?,?,?);
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, job.getPosition());
            preparedStatement.setString(2, job.getProfession());
            preparedStatement.setString(3, job.getDescription());
            preparedStatement.setInt(4, job.getExperience());
            preparedStatement.executeUpdate();
            System.out.println("Successfully added!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Job getJobById(Long jobId) {
        String query = """
                SELECT * FROM job WHERE id = ?
                """;
        Job job = new Job();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,jobId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
            }
            resultSet.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return job;
    }

    public List<Job> sortByExperience(String ascOrDesc) {
        String query = null;
        if (ascOrDesc.equals("desc")) {
            query = """
                SELECT * FROM job ORDER BY experience desc;
                """;
        }else {
            query = """
                SELECT * FROM job ORDER BY experience;
                """;
        }
        List<Job> jobList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                jobList.add(new Job(resultSet.getLong("id"),
                        resultSet.getString("position"),
                        resultSet.getString("profession"),
                        resultSet.getString("description"),
                        resultSet.getInt("experience")));
            }
            resultSet.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return jobList;
    }

    public Job getJobByEmployeeId(Long employeeId) {
        String query = """
                SELECT * FROM job as j JOIN employee e on e.job_id = j.id WHERE e.id = ?; 
                """;
        Job job = new Job();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));

                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setExperience(resultSet.getInt("experience"));
            }
            resultSet.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return job;
    }

    public void deleteDescriptionColumn() {
        String query = "ALTER TABLE job DROP COLUMN description";
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println("Successfully deleted!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropTable() {
        String query = "DROP TABLE job CASCADE ";
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println("Table 'Job' successfully deleted!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void cleanTable() {
        String query = "TRUNCATE job CASCADE";
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println("Table 'Job' successfully cleared!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void showAllTables() {
        String sql = """
                SELECT table_name FROM information_schema.tables 
                WHERE table_schema = 'public';
                """;
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                System.out.println(resultSet.getString("table_name"));
            }
            resultSet.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateJob(Long id, Job job) {
        String query = """
                UPDATE job
                SET position = ?,
                profession = ?,
                description = ?,
                experience = ?
                WHERE id = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,job.getPosition());
            preparedStatement.setString(2,job.getProfession());
            preparedStatement.setString(3,job.getDescription());
            preparedStatement.setInt(4,job.getExperience());
            preparedStatement.setLong(5,id);
            preparedStatement.executeUpdate();
            System.out.println("Successfully updated!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void addColumn() {
        String query = """
               ALTER TABLE job ADD COLUMN description VARCHAR;
               """;
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println("Description successfully added!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
