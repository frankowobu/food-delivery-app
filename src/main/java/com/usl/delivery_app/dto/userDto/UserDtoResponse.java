package com.usl.delivery_app.dto.userDto;

import com.usl.delivery_app.data.Usersdata.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoResponse {
     UserDto user;
    private String token;

    public UserDtoResponse(Users user, String token) {
        this.user = new UserDto(user);
        this.token = token;
    }
}
