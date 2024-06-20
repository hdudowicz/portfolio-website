package com.hddev.portfoliobackend.service

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthenticationService {

    fun getCurrentUser(): KeycloakAuthenticationToken? {
        val authentication = SecurityContextHolder.getContext().authentication
        return if (authentication is KeycloakAuthenticationToken) {
            authentication
        } else {
            null
        }
    }

    fun isAdmin(): Boolean {
        val user = getCurrentUser()
        return user?.authorities?.any { it.authority == "ROLE_ADMIN" } ?: false
    }
}