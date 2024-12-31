package com.usl.delivery_app.service.userService;

import com.usl.delivery_app.data.MealData.Meal;
import com.usl.delivery_app.data.OrderData.Order;
import com.usl.delivery_app.data.Usersdata.Role;
import com.usl.delivery_app.data.Usersdata.Users;
import com.usl.delivery_app.dto.userDto.UserDtoResponse;
import com.usl.delivery_app.repository.MealRepository;
import com.usl.delivery_app.repository.OrderRepository;
import com.usl.delivery_app.repository.UsersRepository;
import com.usl.delivery_app.util.JwtTokenUtil;
import com.usl.delivery_app.util.PasswordEncoderFactory;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MealRepository mealRepository;

    PasswordEncoderFactory passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UserDtoResponse saveUser(Users user) {
        user.setPassword(passwordEncoder.passwordEncoder().encode(user.getPassword()));
        user.setRole(Role.CUSTOMER);
        Users savedUser = usersRepository.save(user);
        String token = jwtTokenUtil.generateToken(savedUser.getUsername());
        return  new UserDtoResponse(savedUser, token);
    }

    @Override
    public Users getUserByEmail(String email) {
       Users user = usersRepository.findByEmail(email);
         return user;
    }

    @Override
    public Users getUserById(Long id) {
        Users user = usersRepository.findById(id).orElse(null);
        return user;
    }

    @Override
    public Users updateUser(Users user) {
        user.setRole(Role.CUSTOMER);
        return usersRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public List<Order> getUserOrder(Long id) {
        Users user = usersRepository.findById(id).orElse(null);
        return user.getOrders();

    }

    @Override
    public List<Users> GetAllUsers() {
        List<Users> user = usersRepository.findAll();
        return user;

    }

    @Override
    public Users addToFavourites(Long userId, Long mealId) {
        Users user = usersRepository.findById(userId).orElse(null);
        Meal meal = mealRepository.findById(mealId).orElse(null); // Assuming you have a MealRepository
        if (user != null && meal != null) {
            user.getFavourite().add(meal);
            return usersRepository.save(user);
        }
        return null;
    }

    @Override
    public Users removeFromFavourites(Long userId, Long mealId) {
        Users user = usersRepository.findById(userId).orElse(null);
        Meal meal = mealRepository.findById(mealId).orElse(null); // Assuming you have a MealRepository
        if (user != null && meal != null) {
            user.getFavourite().remove(meal);
            return usersRepository.save(user);
        }
        return null;
    }

    @Override
    public Users getUserFavourites(Long userId) {
        Users user = usersRepository.findById(userId).orElse(null);
 user.getFavourite();
        return user;
    }

}
