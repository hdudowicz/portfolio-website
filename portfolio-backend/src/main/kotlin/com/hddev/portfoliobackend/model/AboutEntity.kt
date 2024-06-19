package com.hddev.portfoliobackend.model

import jakarta.persistence.*

@Entity
data class AboutEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(columnDefinition = "TEXT")
    val professionalBackground: String,
    @Column(columnDefinition = "TEXT")
    val education: String,
    @ElementCollection
    val certifications: List<String>,
    @ElementCollection
    val personalInterests: List<String>,
    val profilePicture: String?
)
