package com.ch3_scheduler.repository;

import com.ch3_scheduler.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
// Spring Data JPA를 사용하여 데이터베이스 접근 로직을 처리하는 리포지토리 인터페이스입니다.
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // 특정 작성자 이름과 정확히 일치하는 일정들을 찾고, 수정일기준 내림차순으로 정렬하여 반환합니다.
    List<Schedule> findAllByNameOrderByModifiedAtDesc(String name);
    // 모든 일정을 수정일기준 내림차순으로 정렬하여 반환합니다.
    List<Schedule> findAllByOrderByModifiedAtDesc();
}
