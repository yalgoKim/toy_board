package com.example.board0309.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
public class SecurityConfig {

    private final String[] CORS_ALLOW_METHOD = {"GET", "POST", "PATCH", "DELETE", "OPTIONS"};

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedMethods(Arrays.asList(CORS_ALLOW_METHOD));
        configuration.addAllowedHeader("*");
        configuration.addAllowedOrigin("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/login", "/permitAll/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer customWebSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/csrf" ,  "/" ,"/swagger-resources/**" ,"/swagger-ui.html/**" , "/v2/api-docs/**" , "/webjars/**");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
