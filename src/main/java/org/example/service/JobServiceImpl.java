package org.example.service;

import org.example.dao.JobDao;
import org.example.dao.JobDaoImpl;
import org.example.model.Job;

import java.sql.SQLType;
import java.util.List;

public class JobServiceImpl implements JobService{
    private JobDao jobDao = new JobDaoImpl();
    public void createJobTable() {
        jobDao.createJobTable();
    }

    public void addJob(Job job) {
        jobDao.addJob(job);
    }

    public Job getJobById(Long jobId) {
        return jobDao.getJobById(jobId);
    }

    public List<Job> sortByExperience(String ascOrDesc) {
        return jobDao.sortByExperience(ascOrDesc);
    }

    public Job getJobByEmployeeId(Long employeeId) {
        return jobDao.getJobByEmployeeId(employeeId);
    }

    public void deleteDescriptionColumn() {
        jobDao.deleteDescriptionColumn();
    }

    @Override
    public void dropTable() {
        jobDao.dropTable();
    }

    @Override
    public void cleanTable() {
        jobDao.cleanTable();
    }

    @Override
    public void updateJob(Long id, Job job) {
        jobDao.updateJob(id,job);
    }

    @Override
    public void showAllTables() {
        jobDao.showAllTables();
    }

    @Override
    public void addColumn() {
        jobDao.addColumn();
    }
}
