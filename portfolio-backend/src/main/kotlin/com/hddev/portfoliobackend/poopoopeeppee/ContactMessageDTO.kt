package com.hddev.portfoliobackend.poopoopeeppee

import com.hddev.portfoliobackend.entities.ContactMessageEntity

// ContactMessageDTO.kt
data class ContactMessageDTO(
    val id: Long?,
    val name: String,
    val email: String,
    val subject: String,
    val message: String
)

fun ContactMessageEntity.toDTO() = ContactMessageDTO(
    id = id,
    name = name,
    email = email,
    subject = subject,
    message = message
)