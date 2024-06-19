package com.hddev.portfoliobackend.model

import com.hddev.portfoliobackend.entities.UserEntity

data class UserDTO(
    val id: Long?,
    val username: String,
    val email: String,
    val password: String,
    val role: String,
    val projects: List<ProjectDTO>,
    val articles: List<ArticleDTO>
)

fun UserEntity.toDTO() = UserDTO(
    id = id,
    username = username,
    email = email,
    password = password,
    role = role,
    projects = projects.map { it.toDTO() },
    articles = articles.map { it.toDTO() }
)