package com.dep.auth.service.dto;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class UserSessionDTO {

    private String id;

    private String createdBy;

    private Timestamp createdTime;

    private String updatedBy;

    private Timestamp updatedTime;

    private String token;

    private String userName;

    private Timestamp expiredTime;

    private Integer isActive;
}
