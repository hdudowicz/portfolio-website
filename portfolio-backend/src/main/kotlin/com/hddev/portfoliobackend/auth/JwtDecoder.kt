package com.hddev.portfoliobackend.auth

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver
import org.keycloak.adapters.springsecurity.KeycloakConfiguration
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties

@Bean
fun jwtDecoder(properties: OAuth2ResourceServerProperties): JwtDecoder {
    return NimbusJwtDecoder.withJwkSetUri(properties.jwt.issuerUri).build()
}