package com.ch3_scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Ch3SchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch3SchedulerApplication.class, args);
    }

}
