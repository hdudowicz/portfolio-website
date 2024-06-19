package com.hddev.portfoliobackend.repository

import com.hddev.portfoliobackend.model.AboutEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AboutRepository : JpaRepository<AboutEntity, Long>