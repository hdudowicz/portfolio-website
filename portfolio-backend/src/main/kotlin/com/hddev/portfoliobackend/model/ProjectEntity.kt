package com.hddev.portfoliobackend.model

import jakarta.persistence.*

@Entity
data class ProjectEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val title: String,
    val description: String,
    @ElementCollection
    val technologies: List<String>,
    @ElementCollection
    val screenshots: List<String>,
    val liveDemoLink: String?,
    val sourceCodeLink: String?,
    @ManyToOne
    val author: AuthorEntity,
    @ManyToMany
    val relatedArticles: List<ArticleEntity> = emptyList(),
    @ManyToMany
    val relatedProjects: List<ProjectEntity> = emptyList()
)
