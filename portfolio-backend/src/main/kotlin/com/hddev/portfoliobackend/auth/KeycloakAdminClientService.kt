package com.hddev.portfoliobackend.auth

import com.hddev.portfoliobackend.configuration.KeycloakProvider
import org.keycloak.admin.client.resource.UsersResource
import org.keycloak.representations.idm.UserRepresentation
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class KeycloakAdminClientService(private val kcProvider: KeycloakProvider) {
    @Value("\${keycloak.realm}")
    lateinit var realm: String

    fun getAllUsers(): List<UserRepresentation> {
        val usersResource: UsersResource = kcProvider.getInstance().realm(realm).users()
        return usersResource.list()
    }
}