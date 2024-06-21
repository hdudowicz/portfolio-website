package com.hddev.portfoliobackend.model

import com.hddev.portfoliobackend.entities.ArticleEntity

data class ArticleDTO(
    val id: Long?,
    val title: String,
    val content: String,
    val publicationDate: String,
    val authorId: Long
)

fun ArticleEntity.toDTO() = ArticleDTO(
    id = id,
    title = title,
    content = content,
    publicationDate = publicationDate.toString(),
    authorId = author.id
)