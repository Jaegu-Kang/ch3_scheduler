package com.ch3_scheduler.dto;

import lombok.Getter;

import java.time.LocalDateTime;
// 다건/단일 일정 조회 시 클라이언트에게 반환할 데이터를 담는 DTO입니다.
@Getter
public class FindScheduleResponse {

    // 데이터의 불변성을 위해 final로 선언합니다.
    private final Long id;
    private final String title;
    private final String content;
    private final String name;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;
    // 조회된 데이터를 기반으로 응답 객체를 초기화 하는 생성자입니다.
    public FindScheduleResponse(Long id, String title, String content, String name, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
