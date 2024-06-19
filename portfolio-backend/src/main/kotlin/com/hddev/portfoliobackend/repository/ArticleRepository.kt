package com.hddev.portfoliobackend.repository

import com.hddev.portfoliobackend.model.ArticleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository : JpaRepository<ArticleEntity, Long> {
    fun findByAuthorId(authorId: Long): List<ArticleEntity>
}