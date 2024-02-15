package ru.interview.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import ru.interview.platform.handler.AuthenticationSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/favicon.ico"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationSuccessHandler authenticationSuccessHandler) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(AUTH_WHITELIST).permitAll();
                    auth.anyRequest().authenticated();
                })
                .oauth2Login()
                .authorizationEndpoint()
                .baseUri("/api/oauth2/authorization")
                .and()
                .successHandler(authenticationSuccessHandler)
                .and()
                .build();

    }
}
