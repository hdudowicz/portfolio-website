package com.hddev.portfoliobackend.repository

import com.hddev.portfoliobackend.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * The UserRepository interface handles database operations related to the User entity.
 * It extends JpaRepository to leverage built-in methods like save(), findOne(), findAll(), count(), delete() etc.
 * Custom access methods can also be defined.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
@Repository
interface UserRepository : JpaRepository<UserEntity, String> {
    /**
     * A method to find a user by their username.
     * Spring Data JPA will automatically generate the implementation based on the method name.
     *
     * @param username The username of the user to find.
     * @return A nullable User object.
     */
    fun findByUsername(username: String): UserEntity?

    fun findByEmail(email: String): UserEntity?
}
