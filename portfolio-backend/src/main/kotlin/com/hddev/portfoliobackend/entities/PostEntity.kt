package com.hddev.portfoliobackend.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "post")
data class PostEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String,
    @Column
    val title: String = "",
    @Column
    val content: String = "",
    @Column(nullable = false)
    var author: String,
    @ManyToMany var categories: List<CategoryEntity> = listOf(),
    @Column
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(nullable = true)
    val updatedAt: LocalDateTime? = null
)