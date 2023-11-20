package com.dep.auth.service.dto;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class PortalUserInfoDTO {

    private String username;
    private List<String> dptment;
    private String wxId;
    private String deptId;
    private Integer id;
    private String slackId;
    private Boolean isActive;
    private String nickname;
    private String mobile;
    private String email;
    private Date createdAt;
    private Date updatedAt;

}
