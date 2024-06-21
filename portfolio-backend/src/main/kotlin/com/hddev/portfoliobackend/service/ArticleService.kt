package com.hddev.portfoliobackend.service

import com.hddev.portfoliobackend.entities.ArticleEntity
import com.hddev.portfoliobackend.model.ArticleRequest
import com.hddev.portfoliobackend.repository.ArticleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ArticleService(private val articleRepository: ArticleRepository) {

    fun getAllArticles(): List<ArticleEntity> {
        return articleRepository.findAll()
    }

    fun getArticleById(articleId: String ): ArticleEntity? {
        return articleRepository.findByIdOrNull(articleId)
    }

    fun createArticle(articleRequest: ArticleRequest): ArticleEntity {
        val article = ArticleEntity(
            title = articleRequest.title,
            content = articleRequest.content,
            publicationDate = articleRequest.publicationDate.toLocalDate(),
            authorId = articleRequest.authorId
        )
        return articleRepository.save(article)
    }

    fun updateArticle(articleId: String , articleRequest: ArticleRequest): ArticleEntity? {
        val existingArticle = articleRepository.findByIdOrNull(articleId) ?: return null
        val updatedArticle = existingArticle.copy(
            title = articleRequest.title,
            content = articleRequest.content,
            publicationDate = articleRequest.publicationDate.toLocalDate(),
            authorId = articleRequest.authorId
        )
        return articleRepository.save(updatedArticle)
    }

    fun deleteArticle(articleId: String ) {
        articleRepository.deleteById(articleId)
    }
}