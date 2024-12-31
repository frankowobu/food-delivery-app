package com.usl.delivery_app.service.userService;

import com.usl.delivery_app.data.OrderData.Order;
import com.usl.delivery_app.data.Usersdata.Users;
import com.usl.delivery_app.dto.userDto.UserDtoResponse;

import java.util.List;


public interface UserService {
    Users  getUserByEmail(String email);
    Users  getUserById(Long id);
    UserDtoResponse saveUser(Users users);
    Users  updateUser(Users users);
    void deleteUser(Long id);
    List<Order> getUserOrder(Long id);
    List<Users> GetAllUsers();
    Users addToFavourites(Long userId, Long mealId);
    Users removeFromFavourites(Long userId, Long mealId);
    Users getUserFavourites(Long userId);
}
