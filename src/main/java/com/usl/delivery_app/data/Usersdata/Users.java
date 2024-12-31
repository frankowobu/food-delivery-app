package com.usl.delivery_app.data.Usersdata;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.usl.delivery_app.data.MealData.Meal;
import com.usl.delivery_app.data.OrderData.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
@Table(name = "USERS")
@RequiredArgsConstructor
@AllArgsConstructor
public class Users implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName",nullable = false)
    private String firstName;

    @Column(name = "lastName",nullable = false)
    private String lastName;


    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "created_on", updatable = false, nullable = false)
    private Date createdOn;

    @Column(name = "email_verified",nullable = false)
    private boolean emailVerified;

    @Column(name = "verification_token",length = 6)
    private String verificationToken;

    @Column(name = "token_expiration_time")
    private Date tokenExpirationTime;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_favourite_meal",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id"))
    private Set<Meal> favourite = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}