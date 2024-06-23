package com.hddev.portfoliobackend.repository

import com.hddev.portfoliobackend.entities.AuthorEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepository : JpaRepository<AuthorEntity, Long> {
    fun findByEmail(email: String): AuthorEntity?
}
