package com.bankapp.cards.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @ToString
public class BaseEntity {
    @Column(updatable = false, name = "created_at")
    @CreatedDate
    private LocalDateTime createdDate;
    @Column(updatable = false, name = "created_by")
    @CreatedBy
    private String createdBy;
    @Column(insertable = false, name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedDate;
    @Column(insertable = false, name = "updated_by")
    @LastModifiedBy
    private String updatedBy;

}
