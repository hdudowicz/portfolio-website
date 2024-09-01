package com.hddev.portfoliobackend.service

import com.hddev.portfoliobackend.entities.ArticleEntity
import com.hddev.portfoliobackend.model.ArticleDTO
import com.hddev.portfoliobackend.model.ArticleRequest
import com.hddev.portfoliobackend.repository.ArticleRepository
import com.hddev.portfoliobackend.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class ArticleService(private val articleRepository: ArticleRepository, private val userRepository: UserRepository) {
    fun getAllArticles(): List<ArticleEntity> {
        return articleRepository.findAll()
    }

    fun getArticleById(articleId: String): ArticleEntity? {
        return articleRepository.findByIdOrNull(articleId)
    }

    fun createArticle(articleRequest: ArticleDTO): ArticleEntity {
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        val publicationDate = LocalDate.parse(articleRequest.publicationDate, formatter)

        val article =
            ArticleEntity(
                title = articleRequest.title,
                content = articleRequest.content,
                publicationDate = publicationDate,
                user = userRepository.findByIdOrNull(articleRequest.userId)
                    ?: throw NoSuchElementException("User not found"),
            )
        return articleRepository.save(article)
    }

    fun updateArticle(
        articleId: String,
        articleRequest: ArticleRequest,
    ): ArticleEntity? {
        val existingArticle = articleRepository.findByIdOrNull(articleId) ?: return null
        val updatedArticle =
            existingArticle.copy(
                title = articleRequest.title,
                content = articleRequest.content,
                publicationDate = articleRequest.publicationDate.toLocalDate(),
                user = userRepository.findByIdOrNull(articleRequest.userId)
                    ?: throw NoSuchElementException("User not found"),
            )
        return articleRepository.save(updatedArticle)
    }

    fun deleteArticle(articleId: String) {
        articleRepository.deleteById(articleId)
    }
}
