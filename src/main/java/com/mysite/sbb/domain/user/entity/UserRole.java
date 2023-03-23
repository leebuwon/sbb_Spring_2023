package com.mysite.sbb.domain.user.entity;

import lombok.Getter;

@Getter
public enum UserRole {

    ANDIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    UserRole(String value){
        this.value = value;
    }

    private String value;
}
