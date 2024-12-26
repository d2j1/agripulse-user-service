package com.app.agripulse_userservice.config;


import com.app.agripulse_userservice.auth.OtpAuthenticationProvider;
import com.app.agripulse_userservice.auth.OtpAuthenticationToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final OtpAuthenticationProvider otpAuthenticationProvider;

    public SecurityConfig(OtpAuthenticationProvider otpAuthenticationProvider){
        this.otpAuthenticationProvider = otpAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Using lambda expression to disable CSRF
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/login", "/users/signup", "/logout", "/users/").permitAll()
//                        .anyRequest().authenticated()
//                );
//
//        return http.build();
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/users/login","/users/signup", "/users/generateotp").permitAll() // Allow these paths without authentication
                        .anyRequest().authenticated() // Require authentication for other paths
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(otpAuthenticationProvider);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return otpAuthenticationProvider;
    }
}