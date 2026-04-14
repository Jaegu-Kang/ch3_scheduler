package com.ch3_scheduler.service;

import com.ch3_scheduler.dto.*;
import com.ch3_scheduler.entity.Schedule;
import com.ch3_scheduler.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
// 비즈니스 로직을 전담하는 서비스 계층입니다.
@Service
// 생성자를 자동으로 생성하게 해주는 어노테이션입니다.
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 새로운 일정을 생성하는 메서드입니다.
    // 메서드 실행시 트랜젝션을 시작하고 정상 종료되면 커밋, 실패하면 롤백을 수행합니다.
    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request){
        //DTO로 전달받은 클라이언트의 데이터를 기반으로 새로운 앤티티 객체를 생성합니다.
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getName(),
                request.getPassword()
        );
        // 생성된 엔티티를 데이터베이스에 저장합니다.
        Schedule saveSchedule = scheduleRepository.save(schedule);
        // 저장된 엔티티 데이터를 기반으로 클라이언트에게 돌려줄 리스폰스 DTO를 생성합니다.
        return new CreateScheduleResponse(
                saveSchedule.getId(),
                saveSchedule.getTitle(),
                saveSchedule.getContent(),
                saveSchedule.getName(),
                saveSchedule.getCreatedAt(),
                saveSchedule.getModifiedAt()// 오타수정 getCreatedAt() -> getModifiedAt()
        );
    }
    // 이름으로 필터링한 일정을 조회하거나 전체 일정을 조회하는 메서드입니다.
    // 단순 조회용 트랜젝션임을 명시합니다.
    @Transactional(readOnly = true)
    public List<FindScheduleResponse> getAllSchedules(String name){
        List<Schedule> allSchedule;
        // 컨트롤러에서 넘어온 이름이 존재한다면 이름으로 필터링한 일정 목록을 내림차순으로 가져옵니다.
        if (name != null && !name.isEmpty()){
            allSchedule = scheduleRepository.findAllByNameOrderByModifiedAtDesc(name);
        } else {
            // 이름이 없다면 전체 일정 목록을 내림차순으로 가져옵니다.
            allSchedule = scheduleRepository.findAllByOrderByModifiedAtDesc();
        }
        // 엔티티 리스트를 DTO리스트로 변환해 담을 빈 리스트입니다.
        List<FindScheduleResponse> scheduleLists = new ArrayList<>();
        for (Schedule schedule : allSchedule){
            // 조회된 엔티티를 클라이언트 응답 규격에 맞는 DTO로 변환합니다.
            FindScheduleResponse scheduleList = new FindScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getName(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            // 변환된 DTO를 리스트에 추가합니다.
            scheduleLists.add(scheduleList);
        }
        // 클라이언트에게 변환된 DTO를 반환합니다.
        return scheduleLists;

    }

    // 특정 id의 단일 일정을 조회하는 메서드입니다.
    // 단순 조회용 트랜젝션임을 명시합니다.
    @Transactional(readOnly = true)
     public FindScheduleResponse getOneSchedule(Long id){
        // id로 일정을 찾고 없다면 예외처리합니다.
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 일정 입니다.")
        );

        // 조회된 엔티티 데이터를 클라이언트 응답 규격에 맞는 DTO로 변환하여 반환합니다.
        return new FindScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 기존 일정을 수정하는 메서드입니다.
    // 데이터베이스의 변경이 일어나므로 읽기 전용을 명시하지 않습니다.
    @Transactional
    public UpdateScheduleResponse update(Long id, UpdateScheduleRequest request) {
        // id를 조회하여 일정을 가져오고 없다면 예외처리합니다.
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 일정 입니다.")
        );
        // 데이터베이스에 저장된 비밀번호와 클라이언트가 보낸 비밀번호를 비교 검증하며 다르다면 예외처리합니다.
        if (!schedule.getPassword().equals(request.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // 엔티티의 값을 바꿔주면 트랜젝션이 종료될때 스프링이 UPDATE 쿼리를 실행합니다. JPA의 더티 체킹 기능
        schedule.update(
                request.getTitle(),
                request.getName()
        );
        // 수정된 엔티티 데이터를 클라이언트 응답 규격에 맞는 DTO로 변환하여 반환합니다.
        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 특정 일정을 데이터베이스에서 삭제하는 메서드입니다.
    @Transactional
    public void deleteSchedule(Long id, DeleteScheduleRequest request) {
        // id로 일정을 조회하며 없다면 예외처리합니다.
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 일정 입니다.")
        );
        // 데이터베이스에 저장된 비밀번호와 클라이언트가 보낸 비밀번호를 비교 검증하며 다르다면 예외처리합니다.
        if (!schedule.getPassword().equals(request.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // 리포지토리의 delete 메서드를 호출하여 데이터를 삭제합니다.
        scheduleRepository.delete(schedule);
    }
}
