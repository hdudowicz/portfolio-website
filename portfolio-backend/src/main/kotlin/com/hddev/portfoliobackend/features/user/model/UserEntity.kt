package com.hddev.portfoliobackend.features.user.model

import jakarta.persistence.*

/**
 * The User entity represents a user in the system.
 * It is used to store and retrieve user information from the database.
 *
 * @property id The unique identifier for the user.
 * @property username The username of the user.
 * @property password The password of the user, usually stored as a hash for security.
 * @property roles The roles associated with the user, used for authorization.
 */
@Entity
@Table(name = "users")
data class UserEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Column(unique = true)
        var username: String = "",

        @Column
        var password: String = "",

        @ElementCollection(fetch = FetchType.EAGER)
        var roles: Set<String> = emptySet()
)
