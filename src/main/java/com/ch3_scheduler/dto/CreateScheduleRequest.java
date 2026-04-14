package com.ch3_scheduler.dto;


import lombok.Getter;
// 일정 생성 시 클라이언트가 보내는 요청 데이터를 담는 DTO입니다.
@Getter
public class CreateScheduleRequest {

    // 일정 생성을 위해 필요한 제목, 내용, 작성자이름, 비밀번호를 정의합니다.
    private String title;
    private String content;
    private String name;
    private String password;
}
