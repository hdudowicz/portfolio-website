package com.hddev.portfoliobackend.service

import com.hddev.portfoliobackend.entities.UserEntity
import com.hddev.portfoliobackend.repository.UserRepository
import org.keycloak.KeycloakPrincipal
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository,) {
    fun getAllUsers(): List<UserEntity> = userRepository.findAll()

    fun getUserById(id: String): UserEntity? = userRepository.findById(id).orElse(null)

    fun createUser(user: UserEntity): UserEntity = userRepository.save(user)

    fun updateUser(
        id: String,
        newUserDetails: UserEntity,
    ): UserEntity? {
        val user = getUserById(id)
        if (user != null) {
            user.username = newUserDetails.username
            user.email = newUserDetails.email
            user.password = newUserDetails.password
            return userRepository.save(user)
        } else {
            throw Exception("User not found.")
        }
    }

    fun deleteUser(id: String) {
        val user = getUserById(id)
        if (user != null) {
            userRepository.deleteById(id)
        } else {
            throw Exception("User not found.")
        }
    }
}
