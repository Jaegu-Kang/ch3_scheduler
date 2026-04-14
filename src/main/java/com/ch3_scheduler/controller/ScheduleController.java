package com.ch3_scheduler.controller;

import com.ch3_scheduler.dto.*;
import com.ch3_scheduler.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// 응답데이터를 JSON형식으로 반환하게 하는 어노테이션입니다.
@RestController
// 생성자를 자동으로 생성하게 해주는 어노테이션입니다.
@RequiredArgsConstructor
// 이 컨트롤러의 기본 URL경로를 /schedules 로 설정합니다.
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    // POST요청을 통해 새로운 일정을 생성하는 API입니다.
    @PostMapping
    public CreateScheduleResponse createSchedule(@RequestBody CreateScheduleRequest request){
        // Body에 담겨온 요청 데이터를 서비스 계층으로 전달하여 저장합니다.
        return scheduleService.save(request);
    }
    // GET요청을 통해 일정 목록을 조회하는 API 입니다. name 파라미터는 선택사항
    @GetMapping
    public List<FindScheduleResponse> getAllSchedules(@RequestParam(required = false) String name){
        // name 파라미터가 있으면 해당 이름의 일정을, 없으면 전체일정을 조회하여 반환합니다.
        return scheduleService.getAllSchedules(name);
    }
    // GET요청을 통해 특정 id의 단일 일정을 조회하는 API입니다.
    @GetMapping("/{id}")
    public FindScheduleResponse getOneSchedule(@PathVariable Long id){
        // URL 경로 변수로 받은 id를 이용해 해당 일정을 조회합니다.
        return scheduleService.getOneSchedule(id);
    }
    // PUT요청을 총해 특정 id의 일정을 수정하는 API입니다.
    @PutMapping("/{id}")
    public UpdateScheduleResponse update(@PathVariable Long id, @RequestBody UpdateScheduleRequest request){
        // 수정할 대상 일정의 id와 권한 검증용 비밀번호 및 수정 내용이 담긴 요청 데이터를 서비스 계층으로 전달 합니다.
        return scheduleService.update(id,request);
    }
    // DELETE요청을 통해 특정 id의 일정을 삭제하는 API입니다.
    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable Long id, @RequestBody DeleteScheduleRequest request){
        // 삭제할 일정의 id와 권한 검증용 비밀번호가 담긴 데이터를 전달하여 일정을 삭제합니다.
        scheduleService.deleteSchedule(id, request);
    }
}
