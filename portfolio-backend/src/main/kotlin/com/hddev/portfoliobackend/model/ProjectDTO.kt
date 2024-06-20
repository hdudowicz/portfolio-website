package com.hddev.portfoliobackend.model

import com.hddev.portfoliobackend.entities.ProjectEntity

data class ProjectDTO(
    val id: Long?,
    val title: String,
    val description: String,
    val technologies: List<String>,
    val screenshots: List<String>,
    val sourceCodeLink: String?,
    val authorId: Long
)

fun ProjectEntity.toDTO() = ProjectDTO(
    id = id,
    title = title,
    description = description,
    technologies = technologies,
    screenshots = screenshots,
    sourceCodeLink = sourceCodeLink,
    authorId = author.id
)