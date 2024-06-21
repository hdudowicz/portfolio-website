package com.hddev.portfoliobackend.entities

import jakarta.persistence.*

@Entity
data class ProjectEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String = "",
    val title: String,
    val description: String,
    @ElementCollection
    val technologies: List<String>,
    @ElementCollection
    val screenshots: List<String>,
    val liveDemoLink: String?,
    val sourceCodeLink: String?,
    val authorId: String,
    @ManyToMany
    val relatedArticles: List<ArticleEntity> = emptyList(),
    @ManyToMany
    val relatedProjects: List<ProjectEntity> = emptyList()
)
