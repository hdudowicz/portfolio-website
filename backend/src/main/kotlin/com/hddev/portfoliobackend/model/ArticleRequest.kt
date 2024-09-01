package com.hddev.portfoliobackend.model

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class ArticleRequest(
    val title: String,
    val content: String,
    val userId: String,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val publicationDate: LocalDateTime,
)
