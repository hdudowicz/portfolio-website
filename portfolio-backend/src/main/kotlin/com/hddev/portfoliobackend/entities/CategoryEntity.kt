package com.hddev.portfoliobackend.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: String ? = null
}