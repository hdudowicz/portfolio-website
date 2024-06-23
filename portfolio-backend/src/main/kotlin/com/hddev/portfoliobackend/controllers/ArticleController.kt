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

@RestController
@RequestMapping("/api/articles")
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
        @RequestBody articleRequest: ArticleRequest,
    ): ResponseEntity<ArticleEntity> {
        if (!authenticationService.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
        val createdArticle = articleService.createArticle(articleRequest)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArticle)
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
