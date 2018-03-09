package com.maciejak.myplaces_server.api.dto.request;



import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegistrationRequest {

    @NotNull(message = "Username is required")
    @NotEmpty(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must have length between 3-20")
    @Column(unique = true)
    private String username;

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    @Email(message = "Type valid email address")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @Size(min = 3, max = 20, message = "Password must have length between 3-20")
    private String password;

    @NotNull(message = "Confirm password is required")
    @NotEmpty(message = "Confirm password is required")
    private String confirmPassword;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String username, String email, String password, String confirmPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
