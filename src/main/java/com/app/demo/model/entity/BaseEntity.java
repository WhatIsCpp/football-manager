package com.app.demo.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "id_sequence")
    private Long id;

    @Column(name = "data_created_at")
    private LocalDateTime dataCreatedAt;

    @Column(name = "data_updated_at")
    private LocalDateTime dataUpdatedAt;

    @Column
    private Boolean isActive;

    @PrePersist
    void createLocalDateTime() {
        this.dataCreatedAt = this.dataUpdatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void updateLocalDateTime() {
        this.dataUpdatedAt = LocalDateTime.now();
    }
}
