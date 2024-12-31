package com.usl.delivery_app.security;

import com.usl.delivery_app.security.filter.AuthenticationFilter;
import com.usl.delivery_app.security.filter.JwtAuthenticationFilter;
import com.usl.delivery_app.security.manager.CustomAuthenticationManager;
import com.usl.delivery_app.service.userService.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final CustomAuthenticationManager customAuthenticationManager;
    private final UserServiceImpl userService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager, userService);
        authenticationFilter.setFilterProcessesUrl("/api/v1/auth/login");
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/api/v1/auth/**", "/api/v1/auth/signup", "/api/v1/meals/**", "/api/v1/orders/create", "/api/v1/feedback/create","/api/v1/users/order").permitAll()
                        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .antMatchers("/admin/**", "/api/v1/feedback/get-all-feedbacks", "/api/v1/orders/all","/api/v1/meals/create","/api/v1/orders/approve").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, AuthenticationFilter.class)
                .addFilterBefore(authenticationFilter, AuthenticationFilter.class)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers -> headers
                        .addHeaderWriter(new StaticHeadersWriter("Content-Security-Policy", "frame-ancestors 'self'"))
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
