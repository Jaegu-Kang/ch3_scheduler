package com.ch3_scheduler.dto;

import lombok.Getter;
// 일정 삭제시 권한 확인을 위해 클라이언트가 보내는 요청 데이터를 담는 DTO입니다.
@Getter
public class DeleteScheduleRequest {

    // 일정을 삭제할 때 검증용으로 사용할 비밀번호입니다.
    private String password;
}
