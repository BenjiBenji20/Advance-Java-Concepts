package com.azathoth.SimpleCRUD.model;

import org.springframework.stereotype.Component;

@Component
public class UpdateUser {
    private String confirmingUsername;
    private String confirmingPassword;
    private String completeName;
    private String username;
    private String password;

    public UpdateUser(String confirmingUsername, String confirmingPassword, String completeName, String username, String password) {
        this.confirmingUsername = confirmingUsername;
        this.confirmingPassword = confirmingPassword;
        this.completeName = completeName;
        this.username = username;
        this.password = password;
    }

    public UpdateUser(){}

    public String getConfirmingUsername() {
        return confirmingUsername.trim();
    }

    public void setConfirmingUsername(String confirmingUsername) {
        this.confirmingUsername = confirmingUsername;
    }

    public String getConfirmingPassword() {
        return confirmingPassword.trim();
    }

    public void setConfirmingPassword(String confirmingPassword) {
        this.confirmingPassword = confirmingPassword;
    }

    public String getCompleteName() {
        return completeName.trim();
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }

    public String getUsername() {
        return username.trim();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password.trim();
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
