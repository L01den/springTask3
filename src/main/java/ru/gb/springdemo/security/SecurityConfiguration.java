package ru.gb.springdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

/**
 * Created by Lorden on 03.02.2024
 */
@Component
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .authorizeHttpRequests(regestry -> regestry
                        .requestMatchers("/book/**").authenticated()
                        .requestMatchers("/reader/**").hasAnyAuthority("reader")
                        .requestMatchers("/issue/**").hasAnyAuthority("admin")
                        .anyRequest().permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .build();
    }
}
