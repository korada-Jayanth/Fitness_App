package com.fitness.userservice.dto;


import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Format")
    private String email;
    @NotBlank(message = "password is not blank")
    @Size(min = 6, message = "The password should be more the six characters")
    private String password;
    private String firstname;
    private String lastname;
}
