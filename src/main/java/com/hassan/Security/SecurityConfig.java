package com.hassan.Security;


import com.hassan.Security.Encryption.BCryptConfig;
import com.hassan.Security.Filter.JwtFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;


@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final BCryptConfig bCryptConfig;
    private final JwtFilter jwtFilter;


    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(bCryptConfig.encoder());
        return provider;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> {

                    req.requestMatchers("/api/v1/users/register", "/api/v1/users/login", "/api/v1/users/check").permitAll();
                    req.requestMatchers("/api/v1/users").hasRole("ADMIN");
                    req.requestMatchers(HttpMethod.POST, "/api/v1/cars").hasRole("SELLER");
                    req.requestMatchers(HttpMethod.DELETE, "/api/v1/cars").hasRole("SELLER");
                    req.requestMatchers(HttpMethod.PUT, "/api/v1/cars").hasRole("SELLER");
                    req.requestMatchers(HttpMethod.POST, "/api/v1/invoices").hasRole("BUYER");
                    req.requestMatchers(HttpMethod.GET, "/api/v1/invoices/**").hasRole("ADMIN");
                    req.anyRequest().authenticated();
                })
                .httpBasic(withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }



    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


}
