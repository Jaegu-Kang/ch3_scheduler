package com.ch3_scheduler.repository;

import com.ch3_scheduler.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByNameOrderByModifiedAtDesc(String name);
    List<Schedule> findAllByOrderByModifiedAtDesc();
}
