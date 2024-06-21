package com.hddev.portfoliobackend.entities

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "comment")
data class CommentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String = "",
    @Column(nullable = false)
    val authorId: String,
    @Column
    val content: String,
    @Column(nullable = false)
    val publicationDate: LocalDate,
    @ManyToOne
    val article: ArticleEntity
)
