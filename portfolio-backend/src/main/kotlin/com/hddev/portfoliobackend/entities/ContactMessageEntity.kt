package com.hddev.portfoliobackend.entities

import jakarta.persistence.*

@Entity
@Table(name = "contact_message")
data class ContactMessageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column
    val name: String,
    @Column
    val email: String,
    @Column
    val subject: String,
    @Column
    val message: String
)