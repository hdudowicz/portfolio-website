package com.hddev.portfoliobackend.model

import com.hddev.portfoliobackend.entities.UserEntity

data class UserDTO(
    val id: Long?,
    val username: String,
    val email: String,
    val password: String,
    val role: String,
)

fun UserEntity.toDTO() = UserDTO(
    id = id,
    username = username,
    email = email,
    password = password,
    role = role,
)