package com.hddev.portfoliobackend.model

import jakarta.persistence.*

@Entity
data class AuthorEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val email: String,
    val bio: String?,
    @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL], orphanRemoval = true)
    val articles: List<ArticleEntity> = emptyList(),
    @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL], orphanRemoval = true)
    val projects: List<ProjectEntity> = emptyList()
)