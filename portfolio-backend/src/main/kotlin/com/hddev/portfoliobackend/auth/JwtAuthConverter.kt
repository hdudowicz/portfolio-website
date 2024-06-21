package com.hddev.portfoliobackend.auth

import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.stereotype.Component

@Component
class JwtAuthConverter : Converter<Jwt, JwtAuthenticationToken> {

    private val jwtGrantedAuthoritiesConverter = JwtGrantedAuthoritiesConverter()

    override fun convert(jwt: Jwt): JwtAuthenticationToken {
        val authorities: Collection<GrantedAuthority> = jwtGrantedAuthoritiesConverter.convert(jwt) ?: emptyList()
        return JwtAuthenticationToken(jwt, authorities)
    }
}