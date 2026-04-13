package com.ch3_scheduler.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequest {

    private String title;
    private String content;
    private String name;
    private String password;
}
