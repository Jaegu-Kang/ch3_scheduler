package com.ch3_scheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
// 모든 엔티티에 생성일, 수정일을 부여하는 부모 클래스입니다.
@Getter
// 엔티티 클래스들이 이 클래스를 상속할때 해당 필드들도 컬럼으로 인식하게 합니다.
@MappedSuperclass
// 엔티티의 변화를 감지하여 생성일과 수정일을 자동으로 기록하기 위해 JPA Auditing 리스너를 적용합니다.
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    // 엔티티가 생성되어 저장될 때의 시간이 자동 저장됩니다. 수정시에는 변경되지 않습니다.
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // 엔티티의 데이터가 변경될 때 해당 시점이 자동으로 저장됩니다.
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
