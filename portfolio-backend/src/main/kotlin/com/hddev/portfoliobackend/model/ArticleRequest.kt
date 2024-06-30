package com.hddev.portfoliobackend.model

import java.time.LocalDateTime

data class ArticleRequest(
    val title: String,
    val content: String,
    val userId: Long,
    val publicationDate: LocalDateTime,
)
