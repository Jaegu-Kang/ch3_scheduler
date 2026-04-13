package com.ch3_scheduler.controller;

import com.ch3_scheduler.dto.CreateScheduleRequest;
import com.ch3_scheduler.dto.CreateScheduleResponse;
import com.ch3_scheduler.dto.FindScheduleResponse;
import com.ch3_scheduler.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public CreateScheduleResponse createSchedule(@RequestBody CreateScheduleRequest request){
        return scheduleService.save(request);
    }

    @GetMapping
    public List<FindScheduleResponse> getAllSchedules(@RequestParam(required = false) String name){
        return scheduleService.getAllSchedules(name);
    }

    @GetMapping("/{id}")
    public FindScheduleResponse getOneSchedule(@PathVariable Long id){
        return scheduleService.getOneSchedule(id);
    }
}
