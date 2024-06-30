package com.hddev.portfoliobackend.auth

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class AdminRoleEvaluator {
    fun isAdmin(): Boolean {
        val authentication = SecurityContextHolder.getContext().authentication
        return authentication?.authorities?.any { it.authority == "ROLE_ADMIN" } ?: false
    }
}
