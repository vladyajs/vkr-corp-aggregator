package com.vkr.auth.models;

import java.util.Arrays;

public enum Role {
    USER,
    ADMIN,
    REVIEWER;

    public static boolean isValid(String role) {
        return Arrays.stream(Role.values())
                .anyMatch(e -> e.name().equalsIgnoreCase(role));
    }
}
