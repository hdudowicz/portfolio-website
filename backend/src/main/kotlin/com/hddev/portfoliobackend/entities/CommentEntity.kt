package com.hddev.portfoliobackend.entities

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "comment")
data class CommentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity,
    @Column
    val content: String,
    @Column(nullable = false)
    val publicationDate: LocalDate,
    @ManyToOne
    @JoinColumn(name = "article_id")
    val article: ArticleEntity,
)
