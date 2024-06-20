package com.hddev.portfoliobackend.entities

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class ArticleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String = "",
    val title: String,
    @Column(columnDefinition = "TEXT")
    val content: String,

    val authorId: String,
    val publicationDate: LocalDate,
    @OneToMany(mappedBy = "article", cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: List<CommentEntity> = emptyList(),
    @ManyToMany
    val relatedArticles: List<ArticleEntity> = emptyList(),
    @ManyToMany
    val relatedProjects: List<ProjectEntity> = emptyList()
)