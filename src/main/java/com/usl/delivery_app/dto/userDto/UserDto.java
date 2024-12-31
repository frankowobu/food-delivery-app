package com.usl.delivery_app.dto.userDto;

import com.usl.delivery_app.data.MealData.Meal;
import com.usl.delivery_app.data.OrderData.Order;
import com.usl.delivery_app.data.Usersdata.Users;
import com.usl.delivery_app.dto.MealDto.MealDto;
import com.usl.delivery_app.dto.OrdersDto.OrderDto;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date createdOn;
    private boolean emailVerified;
   private List<OrderDto> orders;
    private Set<MealDto> favourite;


    public UserDto(Users user) {
          this.id = user.getId();
          this.firstName = user.getFirstName();
          this.lastName = user.getLastName();
          this.email = user.getEmail();
          this.createdOn = user.getCreatedOn();
          this.emailVerified = user.isEmailVerified();
            this.orders = user.getOrders().stream().map(OrderDto::new).collect(Collectors.toList());
        this.favourite = user.getFavourite().stream().map(MealDto::new).collect(Collectors.toSet());
    }
}
