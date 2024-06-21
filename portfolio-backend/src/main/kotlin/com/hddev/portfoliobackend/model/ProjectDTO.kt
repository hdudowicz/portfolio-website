package com.hddev.portfoliobackend.model

import com.hddev.portfoliobackend.entities.ProjectEntity

data class ProjectDTO(
    val id: String?,
    val title: String,
    val description: String,
    val technologies: String,
    val screenshots: List<String>,
    val sourceCodeLink: String?,
    val authorId: String
)

fun ProjectEntity.toDTO() = ProjectDTO(
    id = id,
    title = title,
    description = description,
    technologies = technologies,
    screenshots = screenshots.map { it.id },
    sourceCodeLink = sourceCodeLink,
    authorId = authorId
)