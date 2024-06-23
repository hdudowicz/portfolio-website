package com.hddev.portfoliobackend.repository

import com.hddev.portfoliobackend.entities.ProjectEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<ProjectEntity, Long> {
    fun findByAuthorId(authorId: Long): List<ProjectEntity>
}
