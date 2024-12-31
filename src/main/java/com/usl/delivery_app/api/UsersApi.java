package com.usl.delivery_app.api;

import com.usl.delivery_app.data.OrderData.Order;
import com.usl.delivery_app.data.Usersdata.Users;
import com.usl.delivery_app.dto.userDto.UserDtoResponse;
import com.usl.delivery_app.service.userService.UserServiceImpl;
import com.usl.delivery_app.util.PasswordEncoderFactory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UsersApi {

    @Autowired
    UserServiceImpl userService;


    PasswordEncoderFactory passwordEncoder;

    @GetMapping("/profile/{id}")
    public ResponseEntity<Users> getUserProfile(@PathVariable Long id) {
        Users user = userService.getUserById(id);
        if (user == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User with id " + id + " not found");
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Users> updateUserProfile(@PathVariable Long id,@RequestBody Users user) {
        Users existingUser = userService.getUserById(id);
        if (existingUser == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User with id " + id + " not found");
        }
        try {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(passwordEncoder.passwordEncoder().encode(user.getPassword()));
            Users updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while updating user profile", e);
        }
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<List<Order>> getUserOrder(@PathVariable Long id) {
        List<Order> orders = userService.getUserOrder(id);
        if (orders.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No orders found for user with id " + id);
        }
        return ResponseEntity.ok(orders);
    }
    @PostMapping("/{userId}/favourites/{mealId}")
    public ResponseEntity<Users> addToFavourites(@PathVariable Long userId, @PathVariable Long mealId) {
        Users user = userService.addToFavourites(userId, mealId);
        if (user == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User or meal not found");
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}/favourites/{mealId}")
    public ResponseEntity<Users> removeFromFavourites(@PathVariable Long userId, @PathVariable Long mealId) {
        Users user = userService.removeFromFavourites(userId, mealId);
        if (user == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User or meal not found");
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}/favourites")
    public ResponseEntity<Users> getUserFavourites(@PathVariable Long userId) {
        Users user = userService.getUserFavourites(userId);
        if (user == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found");
        }
        return ResponseEntity.ok(user);
    }
}