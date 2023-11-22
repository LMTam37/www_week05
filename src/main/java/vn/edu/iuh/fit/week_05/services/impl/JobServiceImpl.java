package vn.edu.iuh.fit.week_05.services.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week_05.models.Company;
import vn.edu.iuh.fit.week_05.models.Job;
import vn.edu.iuh.fit.week_05.repositories.JobRepository;
import vn.edu.iuh.fit.week_05.services.JobService;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository jobRepository;

    @Override
    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public void deleteJobById(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public Job updateJob(Long id, Job updatedJob) {
        Optional<Job> optionalJob = jobRepository.findById(id);

        if (optionalJob.isPresent()) {
            Job existingJob = optionalJob.get();

            if (updatedJob.getJobName() != null && !updatedJob.getJobName().isEmpty()) {
                existingJob.setJobName(updatedJob.getJobName());
            }

            if (updatedJob.getJobDesc() != null && !updatedJob.getJobDesc().isEmpty()) {
                existingJob.setJobDesc(updatedJob.getJobDesc());
            }

            return jobRepository.save(existingJob);
        } else {
            throw new EntityNotFoundException("Job not found with id: " + id);
        }
    }

    @Override
    public List<Job> getJobsByCompany(Company company) {
        return jobRepository.findByCompany(company);
    }
}
