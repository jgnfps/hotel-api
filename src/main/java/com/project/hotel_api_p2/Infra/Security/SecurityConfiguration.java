package com.project.hotel_api_p2.Infra.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Auth público
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                        // Swagger público
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).permitAll()

                        // --- QUARTOS ---
                        .requestMatchers(HttpMethod.POST, "/quartos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/quartos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/quartos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/quartos/**").authenticated()

                        // --- RESERVAS ---
                        .requestMatchers("/reservas/**").authenticated()

                        // --- USUÁRIOS ---
                        .requestMatchers("/usuarios/**").hasRole("ADMIN")

                        // --- HOTEIS ---
                        .requestMatchers(HttpMethod.POST, "/hoteis/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/hoteis/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/hoteis/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/hoteis/**").authenticated()

                        // --- HOSPEDES ---
                        .requestMatchers(HttpMethod.POST, "/hospedes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/hospedes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/hospedes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/hospedes/**").authenticated()

                        // Qualquer outro endpoint precisa de autenticação
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
