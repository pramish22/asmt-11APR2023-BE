package com.citytech.assessment.entity;

import com.citytech.assessment.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Getter
@Setter
public class User {

    @Id
    private String userId;

    @Column
    private String name;

    @Column
    private Role role;

    @Column
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column
    private String createdBy;
}
