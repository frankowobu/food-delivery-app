package com.usl.delivery_app.data.OrderData;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.usl.delivery_app.data.MealData.Meal;
import com.usl.delivery_app.data.Usersdata.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "order_total")
    private double orderTotal;

    @Column(name = "order_address")
    private String orderAddress;

    @Column(name = "order_phone")
    private String orderPhone;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "created_on", updatable = false, nullable = false)
    private Date createdOn;

    @Column(name= "tracking_number")
    private String trackingNumber;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_meals",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id"))
    private List<Meal> meals;

    public Order(Order order) {
        this.id = order.getId();
        this.user = order.getUser();
        this.orderStatus = order.getOrderStatus();
        this.orderTotal = order.getOrderTotal();
        this.orderAddress = order.getOrderAddress();
        this.orderPhone = order.getOrderPhone();
        this.meals = order.getMeals();
    }

// ...







}