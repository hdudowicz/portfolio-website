package com.hddev.portfoliobackend.model

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
@Table(name = "users")
data class UserEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Column(unique = true)
        var username: String = "",

        @Column
        var password: String = "",

        @Column
        var email: String = "",

        @Column
        var bio: String? = null,

        @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL], orphanRemoval = true)
        var articles: List<ArticleEntity> = emptyList(),

        @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL], orphanRemoval = true)
        var projects: List<ProjectEntity> = emptyList()
)