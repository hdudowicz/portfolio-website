package com.hddev.portfoliobackend.interceptors

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfiguration {
    @Bean
    @Throws(Exception::class)
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http
            .oauth2Client()
            .and()
            .oauth2Login()
            .tokenEndpoint()
            .and()
            .userInfoEndpoint()

        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)

        http
            .authorizeHttpRequests()
            .requestMatchers("/unauthenticated", "/oauth2/**", "/login/**").permitAll()
            .anyRequest()
            .fullyAuthenticated()
            .and()
            .logout()
            .logoutSuccessUrl("http://localhost:8080/realms/external/protocol/openid-connect/logout?redirect_uri=http://google.com/")

        return http.build()
    }
}