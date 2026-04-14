package com.ch3_scheduler.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
// 데이터베이스의 테이블과 매핑되는 핵심 엔티티 입니다.
@Getter
@Entity
// 데이터베이스에 생성될 테이블의 이름을 schedules 로 지정합니다.
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    // 이 필드가 테이블의 Primary Key 임을 나타냅니다.
    @Id
    // 기본 키 생성 시 데이터베이스에 자동증가되는 기능입니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // 각 필드의 데이터베이스 컬럼으로 매핑하며 반드시 값이 들어가야하는 NOT NULL 조건을 설정합니다.
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;

    // 새로운 일정을 생성할 때 사용하는 생성자입니다. id와 시간은 자동생성되므로 제외합니다.
    public Schedule(String title, String content, String name, String password){
        this.title = title;
        this.content = content;
        this.name = name;
        this.password = password;
    }

    // 데이터 수정을 처리하는 매서드입니다.
    public void update(String title, String name){
        this.title = title;
        this.name = name;
    }



}
