package com.hddev.portfoliobackend.model

import com.hddev.portfoliobackend.entities.SkillEntity

// SkillDTO.kt
data class SkillDTO(
    val id: Long?,
    val name: String,
    val proficiencyLevel: Int,
    val description: String?
)

fun SkillEntity.toDTO() = SkillDTO(
    id = id,
    name = name,
    proficiencyLevel = proficiencyLevel,
    description = description
)