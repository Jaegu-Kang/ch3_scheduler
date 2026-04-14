package com.ch3_scheduler.dto;

import lombok.Getter;

import java.time.LocalDateTime;
// 일정 생성 후 클라이언트에게 반환할 응답 데이터를 담는 DTO입니다.
@Getter
public class CreateScheduleResponse {

    // 데이터의 불변성을 위해 final로 선언합니다.
    private final Long id;
    private final String title;
    private final String content;
    private final String name;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    // 생성자를 통해 응답 객체 생성시 모든 필드를 초기화 합니다.
    public CreateScheduleResponse(Long id, String title, String content, String name, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
