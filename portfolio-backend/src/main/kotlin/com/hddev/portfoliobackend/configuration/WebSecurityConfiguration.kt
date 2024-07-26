package com.hddev.portfoliobackend.configuration

import com.hddev.portfoliobackend.auth.JwtAuthConverter
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.server.ServerHttpSecurity.http
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebSecurity
class WebSecurityConfiguration(private val jwtAuthConverter: JwtAuthConverter) {

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors().and()
            .csrf().disable()
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/projects").permitAll()
                    .requestMatchers("/articles").hasRole("admin")
                    .requestMatchers("/articles").hasAuthority("SCOPE_create-article")
                    .anyRequest().authenticated()
            }
            .oauth2ResourceServer { oauth2ResourceServer ->
                oauth2ResourceServer
                    .jwt { jwt ->
                        jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
                    }
            }
            .sessionManagement { sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
        return http.build()
    }

    @Bean
    fun jwtAuthenticationConverter(): Converter<Jwt, AbstractAuthenticationToken> {
        val jwtConverter = JwtAuthenticationConverter()
        jwtConverter.setJwtGrantedAuthoritiesConverter(RealmRoleConverter())
        return jwtConverter
    }

    @Bean
    fun jwtDecoder(properties: OAuth2ResourceServerProperties): JwtDecoder {
        return NimbusJwtDecoder.withJwkSetUri(properties.jwt.jwkSetUri).build()
    }
}

class RealmRoleConverter : Converter<Jwt, Collection<GrantedAuthority>> {
    override fun convert(jwt: Jwt): Collection<GrantedAuthority> {
        val authorities = mutableListOf<GrantedAuthority>()

        // Convert roles
        val realmAccess = jwt.claims["realm_access"] as? Map<String, Any>
        val roles = realmAccess?.get("roles") as? List<String> ?: emptyList()
        authorities.addAll(roles.map { role -> SimpleGrantedAuthority("ROLE_$role") })

        // Convert scopes
        val scope = jwt.claims["scope"] as? String
        val scopes = scope?.split(" ") ?: emptyList()
        authorities.addAll(scopes.map { SimpleGrantedAuthority("SCOPE_$it") })

        return authorities
    }
}

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:4200")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true)
    }
}