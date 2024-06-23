package com.hddev.portfoliobackend.auth

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties
import org.springframework.context.annotation.Bean
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder

@Bean
fun jwtDecoder(properties: OAuth2ResourceServerProperties): JwtDecoder {
    return NimbusJwtDecoder.withJwkSetUri(properties.jwt.issuerUri).build()
}
