package com.hddev.portfoliobackend.service

import com.hddev.portfoliobackend.poopoopeeppee.ContactMessageDTO
import com.hddev.portfoliobackend.repository.ContactMessageRepository
import org.springframework.stereotype.Service

@Service
class ContactMessageService(private val contactMessageRepository: ContactMessageRepository) {
    fun getAllContactMessages(): List<ContactMessageDTO> =
        contactMessageRepository.findAll().map { it.toDTO() }

    fun createContactMessage(contactMessageDTO: ContactMessageDTO): ContactMessageDTO {
        val contactMessage = contactMessageDTO.toEntity()
        return contactMessageRepository.save(contactMessage).toDTO()
    }
}
