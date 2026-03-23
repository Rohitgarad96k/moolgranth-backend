package com.moolgranth.backend.dto;

public class LoginRequest {
    private String phone;
    private String password;
    private boolean isAdmin;
    private String secretKey;

    // --- Getters and Setters ---
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean admin) { isAdmin = admin; }

    public String getSecretKey() { return secretKey; }
    public void setSecretKey(String secretKey) { this.secretKey = secretKey; }
}