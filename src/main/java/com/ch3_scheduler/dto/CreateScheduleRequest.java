package com.ch3_scheduler.dto;


import lombok.Getter;

@Getter
public class CreateScheduleRequest {

    private String title;
    private String content;
    private String name;
    private String password;
}
