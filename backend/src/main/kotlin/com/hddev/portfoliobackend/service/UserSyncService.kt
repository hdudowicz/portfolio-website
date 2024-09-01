package com.hddev.portfoliobackend.service

import com.hddev.portfoliobackend.auth.KeycloakAdminClientService
import com.hddev.portfoliobackend.entities.UserEntity
import com.hddev.portfoliobackend.repository.UserRepository
import org.keycloak.representations.idm.UserRepresentation
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserSyncService(
    private val keycloakAdminClientService: KeycloakAdminClientService,
    private val userRepository: UserRepository
) {
    @Transactional
    fun syncUsers() {
        val keycloakUsers = keycloakAdminClientService.getAllUsers()
        keycloakUsers.forEach { keycloakUser ->
            val user = mapKeycloakUserToUserEntity(keycloakUser)
            userRepository.save(user)
        }
    }

    private fun mapKeycloakUserToUserEntity(keycloakUser: UserRepresentation): UserEntity {
        val defaultPassword = "changeMe"

        // Assuming the first role found is the user's role
        val role = keycloakUser.realmRoles?.firstOrNull() ?: "USER"

        return UserEntity(
            id = keycloakUser.id,
            username = keycloakUser.username,
            email = keycloakUser.email,
            password = defaultPassword,
            role = role
        )
    }
}