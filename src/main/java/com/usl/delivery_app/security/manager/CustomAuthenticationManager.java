package com.usl.delivery_app.security.manager;

import com.usl.delivery_app.data.Usersdata.Users;
import com.usl.delivery_app.service.userService.UserServiceImpl;
import com.usl.delivery_app.util.PasswordEncoderFactory;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class CustomAuthenticationManager implements AuthenticationManager {
    private final UserServiceImpl userService;
    private final PasswordEncoderFactory passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Users user = userService.getUserByEmail(authentication.getName());
        if (user == null) {
            throw new BadCredentialsException("User not found");
        }
        if (!passwordEncoder.passwordEncoder().matches(authentication.getCredentials().toString(), user.getPassword())) {
            throw new BadCredentialsException("The password you provided is incorrect");
        }
        List<GrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(user.getRole().name()));

        // Create a UserDetails object
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authority);

        // Return a UsernamePasswordAuthenticationToken with a UserDetails principal
        return new UsernamePasswordAuthenticationToken(userDetails, authentication.getCredentials(), authority);
    }
}
