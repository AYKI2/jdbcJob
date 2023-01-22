package org.example.service;

import org.example.model.Job;

import java.sql.SQLType;
import java.util.List;

public interface JobService {
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
