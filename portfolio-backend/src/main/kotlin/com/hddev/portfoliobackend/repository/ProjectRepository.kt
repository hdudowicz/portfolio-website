package com.hddev.portfoliobackend.repository

import com.hddev.portfoliobackend.entities.ProjectEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<ProjectEntity, String> {
    fun findByAuthorId(authorId: String): List<ProjectEntity>
}