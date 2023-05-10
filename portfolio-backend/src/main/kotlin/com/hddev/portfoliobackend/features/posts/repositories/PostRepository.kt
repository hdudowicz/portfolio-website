package com.hddev.portfoliobackend.features.posts.repositories

import com.hddev.portfoliobackend.features.posts.model.PostEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<PostEntity, Long>