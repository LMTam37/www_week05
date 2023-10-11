package vn.edu.iuh.fit.week_05.services;

import vn.edu.iuh.fit.week_05.models.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {
    Job saveJob(Job job);
    Optional<Job> getJobById(Long id);
    List<Job> getAllJobs();
    void deleteJobById(Long id);
    Job updateJob(Long id, Job updatedJob);
}
