package vn.edu.iuh.fit.week_05.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.week_05.models.Job;
import vn.edu.iuh.fit.week_05.services.JobService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Optional<Job> job = jobService.getJobById(id);
        return job.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Job saveJob(@RequestBody Job job) {
        return jobService.saveJob(job);
    }

    @PutMapping
    public ResponseEntity<Job> updateJob(@RequestBody Job job) {
        try {
            Job jobUpdated = jobService.updateJob(job.getJobId(), job);
            return ResponseEntity.ok(jobUpdated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        try {
            jobService.deleteJobById(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
