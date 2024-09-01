package com.hddev.portfoliobackend.entities

import jakarta.persistence.*

@Entity
@Table(name = "screenshot")
data class ScreenshotEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column
    val url: String,
    @Column(nullable = true)
    val description: String? = null,
    @ManyToOne
    @JoinColumn(name = "project_id")
    val project: ProjectEntity,
)
