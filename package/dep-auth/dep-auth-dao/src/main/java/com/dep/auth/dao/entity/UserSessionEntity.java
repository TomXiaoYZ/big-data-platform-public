package com.dep.auth.dao.entity;

import com.dep.auth.dao.entity.StringBaseEntity;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name = "t_ldap_user_session")
public class UserSessionEntity extends StringBaseEntity{

    @Column(name = "token")
    private String token;

    @Column(name = "username")
    private String userName;

    @Column(name = "expired_time")
    private Timestamp expiredTime;

    @Column(name = "is_active")
    private Integer isActive;
}
