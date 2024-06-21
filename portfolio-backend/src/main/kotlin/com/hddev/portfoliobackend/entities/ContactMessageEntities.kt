package com.hddev.portfoliobackend.entities

import jakarta.persistence.*

@Entity
data class ContactMessageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String = "",
    val name: String,
    val email: String,
    val subject: String,
    @Column(columnDefinition = "TEXT")
    val message: String
)