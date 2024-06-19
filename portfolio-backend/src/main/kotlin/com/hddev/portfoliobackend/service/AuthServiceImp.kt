package com.hddev.portfoliobackend.service

import com.hddev.portfoliobackend.repository.UserRepository
import com.hddev.portfoliobackend.service.interfaces.AuthService
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val userRepository: UserRepository,
    @Value("\${jwt.secret}") private val jwtSecret: String,
    @Value("\${jwt.expiration}") private val jwtExpiration: Long
) : AuthService {
    override fun authenticate(username: String, password: String): String {
        val user = userRepository.findByUsername(username)
        if (user != null && user.password == password && user.role == "ADMIN") {
            return generateToken(user)
        }
        throw UnauthorizedException("Invalid username or password")
    }

    override fun validateToken(token: String): Boolean {
        return try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
            true
        } catch (ex: Exception) {
            false
        }
    }

    private fun generateToken(user: UserEntity): String {
        val now = Date()
        val expiryDate = Date(now.time + jwtExpiration)

        return Jwts.builder()
            .setSubject(user.username)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }
}