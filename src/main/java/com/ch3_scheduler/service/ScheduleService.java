package com.ch3_scheduler.service;

import com.ch3_scheduler.dto.*;
import com.ch3_scheduler.entity.Schedule;
import com.ch3_scheduler.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;


    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request){
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getName(),
                request.getPassword()
        );
        Schedule saveSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                saveSchedule.getId(),
                saveSchedule.getTitle(),
                saveSchedule.getContent(),
                saveSchedule.getName(),
                saveSchedule.getCreatedAt(),
                saveSchedule.getCreatedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<FindScheduleResponse> getAllSchedules(String name){
        List<Schedule> allSchedule;
        if (name != null && !name.isEmpty()){
            allSchedule = scheduleRepository.findAllByNameOrderByModifiedAtDesc(name);
        } else {
            allSchedule = scheduleRepository.findAllByOrderByModifiedAtDesc();
        }
        List<FindScheduleResponse> scheduleLists = new ArrayList<>();
        for (Schedule schedule : allSchedule){
            FindScheduleResponse scheduleList = new FindScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getName(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            scheduleLists.add(scheduleList);
        }
        return scheduleLists;

    }


    @Transactional(readOnly = true)
     public FindScheduleResponse getOneSchedule(Long id){
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 일정 입니다.")
        );

        return new FindScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public UpdateScheduleResponse update(Long id, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 일정 입니다.")
        );
        if (!schedule.getPassword().equals(request.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        schedule.update(
                request.getTitle(),
                request.getName()
        );
        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public void deleteSchedule(Long id, DeleteScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 일정 입니다.")
        );
        if (!schedule.getPassword().equals(request.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        scheduleRepository.delete(schedule);
    }
}
