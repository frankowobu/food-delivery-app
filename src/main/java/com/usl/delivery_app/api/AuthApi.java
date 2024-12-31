package com.usl.delivery_app.api;

import com.usl.delivery_app.data.Usersdata.Users;
import com.usl.delivery_app.dto.userDto.UserDtoResponse;
import com.usl.delivery_app.service.userService.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthApi {

    private UserServiceImpl userService;
    @PostMapping("/signup")
    public ResponseEntity<UserDtoResponse> registerUser(@RequestBody Users user) {
        Users existingUser = userService.getUserByEmail(user.getEmail());
        if (existingUser != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "User with email " + user.getEmail() + " already exists");
        }
        UserDtoResponse response = userService.saveUser(user);
        return ResponseEntity.ok(response);
    }
}
