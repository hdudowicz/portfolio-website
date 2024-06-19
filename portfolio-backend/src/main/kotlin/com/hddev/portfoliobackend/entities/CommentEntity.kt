package com.hddev.portfoliobackend.entities

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class CommentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val author: String,
    @Column(columnDefinition = "TEXT")
    val content: String,
    val publicationDate: LocalDate,
    @ManyToOne
    val article: ArticleEntity
)
