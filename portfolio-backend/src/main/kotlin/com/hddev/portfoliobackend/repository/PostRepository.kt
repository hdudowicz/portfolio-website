package com.hddev.portfoliobackend.repository

import com.hddev.portfoliobackend.entities.PostEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<PostEntity, Long>