package com.hddev.portfoliobackend.entities

import jakarta.persistence.*

@Entity
@Table(name = "author")
data class AuthorEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column
    val name: String,
    @Column
    val email: String,
    @Column(nullable = true)
    val bio: String?
)
