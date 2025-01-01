package com.usl.delivery_app.dto.userDto;

import com.usl.delivery_app.data.Usersdata.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoResponse {
    private UserDto user;
    private String token;
}
