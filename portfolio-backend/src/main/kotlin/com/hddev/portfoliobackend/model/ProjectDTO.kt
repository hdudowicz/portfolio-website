package com.hddev.portfoliobackend.model

import com.hddev.portfoliobackend.entities.ProjectEntity

data class ProjectDTO(
    val id: Long?,
    val title: String,
    val description: String,
    val technologies: String,
    val screenshots: List<Long>,
    val sourceCodeLink: String?,
    val authorId: Long,
)

fun ProjectEntity.toDTO() =
    ProjectDTO(
        id = id,
        title = title,
        description = description,
        technologies = technologies,
        screenshots = screenshots.map { it.id },
        sourceCodeLink = sourceCodeLink,
        authorId = author.id,
    )
