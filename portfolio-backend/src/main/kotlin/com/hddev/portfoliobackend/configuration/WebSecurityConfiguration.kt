package com.hddev.portfoliobackend.configuration

import com.hddev.portfoliobackend.auth.JwtAuthConverter
import lombok.RequiredArgsConstructor
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer



@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
class WebSecurityConfiguration {
    private val jwtAuthConverter: JwtAuthConverter? = JwtAuthConverter()

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.cors().and().csrf().disable()
        http.authorizeHttpRequests()
//            .requestMatchers("/articles/minified").permitAll()
//            .requestMatchers("/articles/create").hasRole("ADMIN")
//            .requestMatchers("/api/articles").permitAll()
            .anyRequest().permitAll()
        http.oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(jwtAuthConverter)
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        return http.build()
    }

    @Bean
    fun jwtDecoder(properties: OAuth2ResourceServerProperties): JwtDecoder {
        return NimbusJwtDecoder.withJwkSetUri(properties.jwt.issuerUri).build()
    }

//    @Bean
//    @Throws(Exception::class)
//    fun configure(http: HttpSecurity): SecurityFilterChain {
//        http
//            .oauth2Client()
//            .and()
//            .oauth2Login()
//            .tokenEndpoint()
//            .and()
//            .userInfoEndpoint()
//
//        http
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//
//        http
//            .authorizeHttpRequests()
//            .requestMatchers("/unauthenticated", "/oauth2/**", "/login/**").permitAll()
//            .anyRequest()
//            .fullyAuthenticated()
//            .and()
//            .logout()
//            .logoutSuccessUrl("http://localhost:8080/realms/external/protocol/openid-connect/logout?redirect_uri=http://google.com/")
//
//        return http.build()
//    }
}

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:4200") // Your Angular app's URL
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true)
    }
}
