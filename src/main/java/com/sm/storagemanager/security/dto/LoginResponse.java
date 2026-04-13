package com.sm.storagemanager.security.dto;

public class LoginResponse {
    private String message;
    private Long branchId;
    private String username;
    private String role;

    public LoginResponse(String message, Long branchId, String username, String role) {
        this.message = message;
        this.branchId = branchId;
        this.username = username;
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public Long getBranchId() {
        return branchId;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
