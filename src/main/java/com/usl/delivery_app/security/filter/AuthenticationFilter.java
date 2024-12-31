package com.usl.delivery_app.security.filter;

import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usl.delivery_app.data.Usersdata.Users;
import com.usl.delivery_app.dto.userDto.UserDto;
import com.usl.delivery_app.dto.userDto.UserDtoResponse;
import com.usl.delivery_app.dto.userDto.UsersLoginDto;
import com.usl.delivery_app.security.SecurityConstant;
import com.usl.delivery_app.service.userService.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private  final UserServiceImpl userService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsersLoginDto users = new ObjectMapper().readValue(request.getInputStream(), UsersLoginDto.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getEmail(), users.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        List<String> roles = authResult.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Cast to UserDetails and retrieve the username
        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        String username = userDetails.getUsername();

        // Use the username to fetch the Users from your database
        // Assuming you have a method in your UserServiceImpl class to get a user by username
        Users user = userService.getUserByEmail(username);

        String token = com.auth0.jwt.JWT.create()
                .withSubject(authResult.getName())
                .withClaim("role", roles)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstant.SECRET_KEY));
        response.addHeader(SecurityConstant.HEADER_STRING, SecurityConstant.TOKEN_PREFIX + token);

        UserDtoResponse userDtoResponse = new UserDtoResponse();
        userDtoResponse.setUser(new UserDto(user));
        userDtoResponse.setToken(token);
        response.getWriter().write(new ObjectMapper().writeValueAsString(userDtoResponse));
        response.getWriter().flush();
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("UNAUTHORIZED");
        response.getWriter().flush();
    }
}