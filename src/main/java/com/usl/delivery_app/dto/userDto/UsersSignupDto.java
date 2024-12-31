package com.usl.delivery_app.dto.userDto;

import lombok.Data;

@Data
public class UsersSignupDto {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
}
