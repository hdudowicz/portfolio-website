package com.hddev.portfoliobackend.configuration


import com.hddev.portfoliobackend.auth.JwtAuthConverter
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
class WebSecurityConfiguration {
    private val jwtAuthConverter: JwtAuthConverter? = null

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.cors().and().csrf().disable()
        http.authorizeHttpRequests()
            .requestMatchers("/articles/minified").permitAll()
            .requestMatchers("/articles/create").hasRole("ADMIN")
            .requestMatchers("/articles/all").hasRole("USER")
            .anyRequest().authenticated()
        http.oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(jwtAuthConverter)
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        return http.build()
    }
}