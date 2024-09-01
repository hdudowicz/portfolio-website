package com.hddev.portfoliobackend.controllers

import com.hddev.portfoliobackend.entities.ArticleEntity
import com.hddev.portfoliobackend.model.ArticleDTO
import com.hddev.portfoliobackend.model.ArticleRequest
import com.hddev.portfoliobackend.model.toDTO
import com.hddev.portfoliobackend.service.ArticleService
import com.hddev.portfoliobackend.service.AuthenticationService
import jakarta.annotation.security.RolesAllowed
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@RestController
@RequestMapping("/articles")
class ArticleController(
    private val authenticationService: AuthenticationService,
    private val articleService: ArticleService,
) {
    @GetMapping
    fun getAllArticles(): ResponseEntity<List<ArticleEntity>> {
        val articles = articleService.getAllArticles()
        return ResponseEntity.ok(articles)
    }

    @GetMapping("/{articleId}")
    fun getArticleById(
        @PathVariable articleId: String,
    ): ResponseEntity<ArticleEntity> {
        val article = articleService.getArticleById(articleId)
        return if (article != null) {
            ResponseEntity.ok(article)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    @RolesAllowed("ROLE_ADMIN")
    fun createArticle(
        @RequestBody articleRequest: ArticleDTO,
    ): ResponseEntity<Any> {
        try {
            // Parse the date string to LocalDateTime
            val formatter = DateTimeFormatter.ISO_DATE_TIME
            val publicationDate = LocalDateTime.parse(articleRequest.publicationDate, formatter)

            // Create ArticleCreateDto with parsed date
            val articleCreateDto = ArticleDTO(
                title = articleRequest.title,
                content = articleRequest.content,
                userId = articleRequest.userId,
                publicationDate = publicationDate.toString()
            )

//            TODO: Check create article component

            val createdArticle = articleService.createArticle(articleCreateDto)
            return ResponseEntity.status(HttpStatus.CREATED).body(createdArticle)
        } catch (e: DateTimeParseException) {
            return ResponseEntity.badRequest().body("Invalid date format, use ISO 8601 format.")
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body("Error creating article: ${e.message}")
        }
    }

    @PutMapping("/{articleId}")
    @RolesAllowed("ROLE_ADMIN")
    fun updateArticle(
        @PathVariable articleId: String,
        @RequestBody articleRequest: ArticleRequest,
    ): ResponseEntity<ArticleDTO> {
        if (!authenticationService.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
        val updatedArticle = articleService.updateArticle(articleId, articleRequest)
        return if (updatedArticle != null) {
            ResponseEntity.ok(updatedArticle.toDTO())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{articleId}")
    @RolesAllowed("ROLE_ADMIN")
    fun deleteArticle(
        @PathVariable articleId: String,
    ): ResponseEntity<Void> {
        if (!authenticationService.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
        articleService.deleteArticle(articleId)
        return ResponseEntity.noContent().build()
    }
}
