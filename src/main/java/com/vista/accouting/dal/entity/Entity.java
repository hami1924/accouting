package com.vista.accouting.dal.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

@Data
public class Entity implements Cloneable{
    @Id
    private String id;
    @Version
    protected Long version;
    protected Boolean deleted = false;
    protected LocalDateTime deletedAt;
    @CreatedDate
    protected LocalDateTime createdDate;
    @LastModifiedDate
    protected LocalDateTime lastModifiedDate;
}
