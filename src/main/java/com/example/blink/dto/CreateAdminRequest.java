package com.example.blink.dto;

public class CreateAdminRequest {
    private String adminName;
    private String email;
    private String password;

    public CreateAdminRequest(String adminName, String email, String password) {
        this.adminName = adminName;
        this.email = email;
        this.password = password;
    }

    public CreateAdminRequest() {
        super();
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
