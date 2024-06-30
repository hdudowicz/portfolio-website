package com.hddev.portfoliobackend.entities

import jakarta.persistence.*

@Entity
@Table(name = "project")
data class ProjectEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column
    val title: String,
    @Column
    val description: String,
    @Column
    val technologies: String,
    @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL], orphanRemoval = true)
    val screenshots: List<ScreenshotEntity> = emptyList(),
    @Column
    val liveDemoLink: String?,
    @Column
    val sourceCodeLink: String?,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity,
    @ManyToMany
    @JoinTable(
        name = "project_article",
        joinColumns = [JoinColumn(name = "project_id")],
        inverseJoinColumns = [JoinColumn(name = "article_id")],
    )
    val relatedArticles: List<ArticleEntity> = emptyList(),
)
