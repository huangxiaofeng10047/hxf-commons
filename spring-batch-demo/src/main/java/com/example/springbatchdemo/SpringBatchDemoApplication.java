package com.example.springbatchdemo;

import com.springboot.zk.annotation.EnableUserClient;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableBatchProcessing
@SpringBootApplication
@EnableUserClient
public class SpringBatchDemoApplication {

	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Autowired
	StepBuilderFactory stepBuilderFactory;

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchDemoApplication.class, args);
	}

	@Bean
	public DemoTaskLet demoTaskLet(){
		return new DemoTaskLet();
	}
	@Bean
	public Job myjob(){
		Step step = this.stepBuilderFactory.get("login").tasklet(demoTaskLet()).build();
		return this.jobBuilderFactory.get("mydemo").start(step).build();
	}

}
