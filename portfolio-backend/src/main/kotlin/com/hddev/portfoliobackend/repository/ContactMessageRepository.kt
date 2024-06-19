package com.hddev.portfoliobackend.repository

import com.hddev.portfoliobackend.model.ContactMessageEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ContactMessageRepository : JpaRepository<ContactMessageEntity, Long>