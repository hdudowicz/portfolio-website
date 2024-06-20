package com.hddev.portfoliobackend.model

import java.time.LocalDateTime

data class ArticleRequest(
    val title: String,
    val content: String,
    val authorId: String,
    val publicationDate: LocalDateTime
)