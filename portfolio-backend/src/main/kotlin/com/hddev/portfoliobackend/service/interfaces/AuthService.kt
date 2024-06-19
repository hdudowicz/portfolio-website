package com.hddev.portfoliobackend.service.interfaces

interface AuthService {
    fun authenticate(username: String, password: String): String
    fun validateToken(token: String): Boolean
}