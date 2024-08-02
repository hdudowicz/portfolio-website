package com.hddev.portfoliobackend.service

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class UserSyncInitializer(private val userSyncService: UserSyncService) {

    @EventListener(ApplicationReadyEvent::class)
    fun initializeUsers() {
        userSyncService.syncUsers()
    }
}