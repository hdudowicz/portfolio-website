package com.hddev.portfoliobackend.repository

import com.hddev.portfoliobackend.entities.SkillEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SkillRepository : JpaRepository<SkillEntity, Long>
