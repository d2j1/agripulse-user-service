package com.app.agripulse_userservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter

public abstract class BaseModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name="created_date", nullable = false, updatable = false)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "updated_date", nullable= false)
    private Date updateDate;

    @PrePersist
    protected void onCreate() {
        createDate = new Date();
        updateDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = new Date();
    }

}
