package com.hddev.portfoliobackend.entities

import jakarta.persistence.*

@Entity
@Table(name = "skill")
data class SkillEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column
    val name: String,
    @Column
    val proficiencyLevel: Int,
    @Column(nullable = true)
    val description: String?,
)
