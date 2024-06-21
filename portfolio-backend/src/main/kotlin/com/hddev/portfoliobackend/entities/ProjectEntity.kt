package com.hddev.portfoliobackend.entities

import jakarta.persistence.*

@Entity
@Table(name = "project")
data class ProjectEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String = "",
    @Column
    val title: String,
    @Column
    val description: String,
    @Column
    val technologies: String,
    @OneToMany
    val screenshots: List<ScreenshotEntity>,
    @Column
    val liveDemoLink: String?,
    @Column
    val sourceCodeLink: String?,
    @Column
    val authorId: String,
    @ManyToMany
    val relatedArticles: List<ArticleEntity> = emptyList(),
)
