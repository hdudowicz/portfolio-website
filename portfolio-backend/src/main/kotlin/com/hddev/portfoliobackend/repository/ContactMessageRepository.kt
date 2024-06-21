package com.hddev.portfoliobackend.repository

import com.hddev.portfoliobackend.entities.ContactMessageEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ContactMessageRepository : JpaRepository<ContactMessageEntity, String>