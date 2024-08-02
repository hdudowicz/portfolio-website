package com.hddev.portfoliobackend.configuration

import org.keycloak.OAuth2Constants
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class KeycloakProvider {
    @Value("\${keycloak.auth-server-url}")
    lateinit var serverURL: String

    @Value("\${keycloak.realm}")
    lateinit var realm: String

    @Value("\${keycloak.resource}")
    lateinit var clientID: String

    @Value("\${keycloak.credentials.secret}")
    lateinit var clientSecret: String

    private var keycloak: Keycloak? = null

    fun getInstance(): Keycloak {
        if (keycloak == null) {
            return KeycloakBuilder.builder()
                .realm(realm)
                .serverUrl(serverURL)
                .clientId(clientID)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build()
        }
        return keycloak!!
    }
}