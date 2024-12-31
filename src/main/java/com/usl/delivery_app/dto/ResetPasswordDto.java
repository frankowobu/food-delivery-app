package com.usl.delivery_app.dto;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private String email;
    private String password;
    private String newPassword;
    private String token;

}
