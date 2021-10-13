package com.example.springbatchdemo;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class ApiController {
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job myjob;
    @GetMapping(value = {"/demo"})
    public void runJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter> context = new HashMap<>();
        //  确保每次都创建新的jobinstance
        context.put("id", new JobParameter(UUID.randomUUID().toString()));
        JobExecution jobExecution= jobLauncher.run(myjob,new JobParameters(context));
        System.out.println(jobExecution.getJobId());
    }
}