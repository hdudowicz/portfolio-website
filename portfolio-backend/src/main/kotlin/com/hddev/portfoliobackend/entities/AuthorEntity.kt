package com.hddev.portfoliobackend.entities

import jakarta.persistence.*

@Entity
data class AuthorEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String = "",
    val name: String,
    val email: String,
    val bio: String?
)
