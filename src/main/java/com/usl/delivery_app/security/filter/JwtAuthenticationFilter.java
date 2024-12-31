package com.usl.delivery_app.security.filter;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static com.usl.delivery_app.security.SecurityConstant.SECRET_KEY;
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authHeader.substring(7);

        try {
            String user = JWT.require(Algorithm.HMAC512(SECRET_KEY.getBytes()))
                    .build()
                    .verify(token)
                    .getSubject();
            if (user != null) {
                Authentication authentication = new UsernamePasswordAuthenticationToken(user,null, Arrays.asList());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request,response);
                return; // Add this line
            }

        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}