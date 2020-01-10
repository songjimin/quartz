package com.example.quartz_test.repository;

import com.example.quartz_test.model.JobHistory;

import org.springframework.data.repository.CrudRepository;

public interface JobHistoryRepository extends CrudRepository<JobHistory , Long> {
}
