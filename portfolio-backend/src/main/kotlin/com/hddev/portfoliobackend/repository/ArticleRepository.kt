package com.hddev.portfoliobackend.repository

import com.hddev.portfoliobackend.entities.ArticleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository : JpaRepository<ArticleEntity, String> {
    fun findByUserId(userId: Long): List<ArticleEntity>
}
