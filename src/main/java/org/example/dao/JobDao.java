package org.example.dao;

import org.example.model.Employee;
import org.example.model.Job;

import java.sql.SQLType;
import java.util.List;

public interface JobDao {
    void createJobTable();
    void addJob(Job job);
    Job getJobById(Long jobId);
    List<Job> sortByExperience(String ascOrDesc);
    Job getJobByEmployeeId(Long employeeId);
    void deleteDescriptionColumn();
    void dropTable();
    void cleanTable();
    void updateJob(Long id, Job job);
    void showAllTables();
    void addColumn();
}
