package com.hddev.portfoliobackend.entities

import jakarta.persistence.*

@Entity
@Table(name = "category")
class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: String ? = null
}