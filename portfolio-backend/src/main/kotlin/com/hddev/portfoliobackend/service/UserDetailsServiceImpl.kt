package com.hddev.portfoliobackend.service

import com.hddev.portfoliobackend.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
        if (user != null) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    .password(user.password)
                    .authorities("USER")
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build()
        } else {
            throw UsernameNotFoundException("User not found.")
        }
    }
}
