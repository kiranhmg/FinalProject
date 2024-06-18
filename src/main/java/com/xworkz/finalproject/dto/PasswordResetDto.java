package com.xworkz.finalproject.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class PasswordResetDto {
    @NotEmpty(message = "please enter password")
    private String password;
    @NotEmpty(message = "please enter email")
    private String email;
    @NotEmpty(message = "please enter  new password")
    private String newpassword;
    @NotEmpty(message = "please enter confirm password")
    private String confirmpassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    @Override
    public String toString() {
        return "PasswordResetDto{" +
                "password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", newpassword='" + newpassword + '\'' +
                ", confirmpassword='" + confirmpassword + '\'' +
                '}';
    }
}
