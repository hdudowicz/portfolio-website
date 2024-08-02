package com.hddev.portfoliobackend.entities

import jakarta.persistence.*

/**
 * The User entity represents a user in the system.
 * It is used to store and retrieve user information from the database.
 *
 * @property id The unique identifier for the user.
 * @property username The username of the user.
 * @property password The password of the user, usually stored as a hash for security.
 */
@Entity
@Table(name = "user")
data class UserEntity(
    @Id
    val id: String,
    @Column(unique = true, nullable = false)
    var username: String,
    @Column(unique = true, nullable = false)
    var email: String,
    @Column(nullable = false)
    var password: String,
    @Column(nullable = false)
    val role: String,
)

