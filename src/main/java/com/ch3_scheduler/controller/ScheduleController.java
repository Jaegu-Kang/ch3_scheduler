package com.ch3_scheduler.controller;

import com.ch3_scheduler.dto.*;
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

    @PutMapping("/{id}")
    public UpdateScheduleResponse update(@PathVariable Long id, @RequestBody UpdateScheduleRequest request){
        return scheduleService.update(id,request);
    }

    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable Long id, @RequestBody DeleteScheduleRequest request){
        scheduleService.deleteSchedule(id, request);
    }
}
