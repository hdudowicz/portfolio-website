package com.hddev.portfoliobackend.auth

import com.hddev.portfoliobackend.configuration.KeycloakProvider
import org.keycloak.admin.client.resource.UsersResource
import org.keycloak.models.AbstractKeycloakTransaction.logger
import org.keycloak.representations.idm.UserRepresentation
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class KeycloakAdminClientService(private val kcProvider: KeycloakProvider) {
    @Value("\${keycloak.realm}")
    lateinit var realm: String

    // TODO: Remove loggers
    fun getAllUsers(): List<UserRepresentation> {
        try {
            logger.debug("Attempting to fetch users from Keycloak")
            val keycloak = kcProvider.getInstance()
            logger.debug("Keycloak instance obtained")
            val realmResource = keycloak.realm(realm)
//            val serverInfo = keycloak.serverInfo()
            logger.debug("Realm resource obtained for realm: $realm")
//            logger.debug("Server info: ${serverInfo.info}")
            val usersResource: UsersResource = realmResource.users()
            logger.debug("Users resource obtained")
            return usersResource.list()
        } catch (e: Exception) {
            logger.error("Error fetching users from Keycloak", e)
            throw e
        }
    }
}