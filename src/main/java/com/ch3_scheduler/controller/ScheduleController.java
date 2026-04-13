package com.ch3_scheduler.controller;

import com.ch3_scheduler.dto.CreateScheduleRequest;
import com.ch3_scheduler.dto.CreateScheduleResponse;
import com.ch3_scheduler.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public CreateScheduleResponse createSchedule(@RequestBody CreateScheduleRequest request){
        return scheduleService.save(request);
    }
}
