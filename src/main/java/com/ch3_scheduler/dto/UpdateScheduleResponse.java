package com.ch3_scheduler.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateScheduleResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String name;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public UpdateScheduleResponse(Long id, String title, String content, String name, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
