package com.hddev.portfoliobackend.repository

import com.hddev.portfoliobackend.entities.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<CommentEntity, String> {
    fun findByArticleId(articleId: String): List<CommentEntity>
}