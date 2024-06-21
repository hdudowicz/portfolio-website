package com.hddev.portfoliobackend.entities

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "article")
data class ArticleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column
    val title: String,
    @Column
    val content: String,
    @ManyToOne
    @JoinColumn(name = "author_id")
    val author: AuthorEntity,
    @Column
    val publicationDate: LocalDate,
    @OneToMany(mappedBy = "article", cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: List<CommentEntity> = emptyList(),
    @ManyToMany(mappedBy = "relatedArticles")
    val relatedProjects: List<ProjectEntity> = emptyList()
)