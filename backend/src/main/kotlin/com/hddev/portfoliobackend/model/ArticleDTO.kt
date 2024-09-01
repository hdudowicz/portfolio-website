package com.hddev.portfoliobackend.model

import com.hddev.portfoliobackend.entities.ArticleEntity

data class ArticleDTO(
    val title: String,
    val content: String,
    val publicationDate: String,
    val userId: String,
)

fun ArticleEntity.toDTO() =
    ArticleDTO(
        title = title,
        content = content,
        publicationDate = publicationDate.toString(),
        userId = user.id,
    )
