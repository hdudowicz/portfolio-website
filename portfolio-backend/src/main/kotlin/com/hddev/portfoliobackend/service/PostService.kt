package com.hddev.portfoliobackend.service

import com.hddev.portfoliobackend.repository.UserRepository
import com.hddev.portfoliobackend.entities.PostEntity
import com.hddev.portfoliobackend.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(private val postRepository: PostRepository,
                  private val userRepository: UserRepository
) {

    fun getAllPosts(): List<PostEntity> = postRepository.findAll()

    fun getPostById(id: String ): PostEntity? = postRepository.findById(id).orElse(null)

    fun createPost(post: PostEntity, username: String): PostEntity {
        val user = userRepository.findByUsername(username)
        if (user != null) {
            TODO()
//            post.author = user.username
            return postRepository.save(post)
        } else {
            throw Exception("User not found.")
        }
    }

    fun deletePost(id: String , username: String) {
        val post = getPostById(id)
        val user = userRepository.findByUsername(username)
        if (post != null) {
            TODO()
//            if (post.author == user?.username) {
//                postRepository.deleteById(id)
//            } else {
//                throw Exception("Permission denied.")
//            }
        } else {
            throw Exception("Post not found.")
        }
    }
}
