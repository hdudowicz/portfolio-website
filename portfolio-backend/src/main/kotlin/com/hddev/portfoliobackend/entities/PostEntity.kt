package com.hddev.portfoliobackend.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "post")
data class PostEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column
    val title: String,
    @Column
    val content: String,
    @ManyToOne
    @JoinColumn(name = "author_id")
    var author: AuthorEntity,
    @ManyToMany
    @JoinTable(
        name = "post_category",
        joinColumns = [JoinColumn(name = "post_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")],
    )
    val categories: List<CategoryEntity> = emptyList(),
    @Column
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(nullable = true)
    val updatedAt: LocalDateTime? = null,
)
