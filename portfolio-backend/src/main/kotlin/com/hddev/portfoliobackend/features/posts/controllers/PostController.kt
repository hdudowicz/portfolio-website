package com.hddev.portfoliobackend.features.posts.controllers

import com.hddev.portfoliobackend.features.posts.model.PostEntity
import com.hddev.portfoliobackend.features.posts.services.PostService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

/**
 * PostController is a REST controller that handles HTTP requests related to blog posts.
 * It uses the PostService to perform operations.
 *
 * @property postService The service which handles operations related to blog posts.
 */
@RestController
@RequestMapping("/api/posts")
class PostController(private val postService: PostService) {

    /**
     * Get all blog posts.
     *
     * @return A list of all blog posts.
     */
    @GetMapping
    fun getAllPosts(): List<PostEntity> = postService.getAllPosts()

    /**
     * Get a specific blog post by its id.
     *
     * @param id The id of the blog post.
     * @return The blog post with the given id.
     */
    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: Long): PostEntity? = postService.getPostById(id)

    /**
     * Create a new blog post.
     * This method is only accessible to users with the "ADMIN" role.
     *
     * @param post The blog post to create.
     * @param username The username of the authenticated user.
     * @return The created blog post.
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun createPost(@RequestBody post: PostEntity, @RequestParam username: String): PostEntity = postService.createPost(post, username)

    /**
     * Delete a blog post by its id.
     * This method is only accessible to users with the "ADMIN" role.
     *
     * @param id The id of the blog post to delete.
     * @param username The username of the authenticated user.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun deletePost(@PathVariable id: Long, @RequestParam username: String) = postService.deletePost(id, username)
}
