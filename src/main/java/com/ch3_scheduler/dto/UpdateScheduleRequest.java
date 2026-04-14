package com.ch3_scheduler.dto;

import lombok.Getter;
// 일정 수정 시 클라이언트가 보낸 데이터를 담는 DTO입니다.
@Getter
public class UpdateScheduleRequest {

    // 변경을 허용할 제목, 이름 필드와 검증용으로 사용할 비밀번호 입니다.
    private String title;
    private String name;
    private String password;
}
