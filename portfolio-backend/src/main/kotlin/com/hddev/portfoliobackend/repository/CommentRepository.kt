package com.hddev.portfoliobackend.repository

import com.hddev.portfoliobackend.model.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<CommentEntity, Long> {
    fun findByArticleId(articleId: Long): List<CommentEntity>
}