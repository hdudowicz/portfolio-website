package com.hddev.portfoliobackend.auth

import com.nimbusds.oauth2.sdk.util.CollectionUtils
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream

@Component
class JwtAuthConverter :
    Converter<Jwt?, AbstractAuthenticationToken?> {
    private val jwtGrantedAuthoritiesConverter = JwtGrantedAuthoritiesConverter()

    override fun convert(jwt: Jwt): AbstractAuthenticationToken {
        val authorities = Stream.concat(
            jwtGrantedAuthoritiesConverter.convert(jwt)!!.stream(),
            extractUserRoles(jwt).stream()
        ).collect(Collectors.toSet())
        return JwtAuthenticationToken(jwt, authorities)
    }

    private fun extractUserRoles(jwt: Jwt): Set<GrantedAuthority> {
        val realmAccess = jwt.getClaim<Map<String, Any>>("realm_access")
        val realmRoles = realmAccess["roles"] as List<String>?

        if (CollectionUtils.isNotEmpty(realmRoles)) {
            return realmRoles!!.stream()
                .map { role: String ->
                    SimpleGrantedAuthority(
                        "ROLE_" + role.uppercase(Locale.getDefault())
                    )
                }
                .collect(Collectors.toSet())
        }

        return emptySet()
    }
}