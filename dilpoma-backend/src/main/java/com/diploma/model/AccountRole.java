package com.diploma.model;

import org.springframework.security.core.GrantedAuthority;

public enum AccountRole implements GrantedAuthority {
    ROLE_USER, ROLE_MODERATOR, ROLE_ADMIN;
    @Override
    public String getAuthority() {
        return name();
    }
}
