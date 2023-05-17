package com.spring.printer.config;

import org.springframework.security.core.GrantedAuthority;

public final class SimpleGrantedAuthority implements GrantedAuthority {

    private String role;

    public SimpleGrantedAuthority(String authority) {
        role = authority;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}

