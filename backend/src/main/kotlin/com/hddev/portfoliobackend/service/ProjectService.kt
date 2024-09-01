package com.hddev.portfoliobackend.service

import com.hddev.portfoliobackend.model.ProjectDTO
import com.hddev.portfoliobackend.repository.ProjectRepository
import com.hddev.portfoliobackend.repository.UserRepository
import com.hddev.portfoliobackend.entities.ProjectEntity
import com.hddev.portfoliobackend.exception.ResourceNotFoundException
import org.springframework.stereotype.Service

@Service
class ProjectService(
    private val projectRepository: ProjectRepository,
    private val userRepository: UserRepository,
) {
    fun getAllProjects(): List<ProjectDTO> =
        projectRepository.findAll().map { it.toDTO() }

    fun getProjectById(id: Long): ProjectDTO? =
        projectRepository.findById(id)
            .map { it.toDTO() }
            .orElse(null)

    fun createProject(projectDTO: ProjectDTO): ProjectDTO {
        val project = projectDTO.toEntity(userRepository)
        return projectRepository.save(project).toDTO()
    }

    fun updateProject(id: Long, projectDTO: ProjectDTO): ProjectDTO? {
        val existingProject = projectRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Project", "id", id) }
        
        existingProject.copy(
            title = projectDTO.title,
                description = projectDTO.description,
//                technologies = projectDTO.technologies,
                sourceCodeLink = projectDTO.sourceCodeLink,
                liveDemoLink = null
        )
        
        return projectRepository.save(existingProject).toDTO()
    }

    fun deleteProject(id: Long): Boolean {
        return if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}

// Extension functions to convert between Entity and DTO
fun ProjectEntity.toDTO() = ProjectDTO(
    id = id,
    title = title,
    description = description,
    technologies = technologies,
//    screenshots = screenshots.map { it.id },
    sourceCodeLink = sourceCodeLink,
    userId = user.id
)

fun ProjectDTO.toEntity(userRepository: UserRepository) = ProjectEntity(
    id = id ?: 0,
    title = title,
    description = description,
    technologies = technologies,
    sourceCodeLink = sourceCodeLink,
    user = userRepository.findById(userId)
        .orElseThrow { ResourceNotFoundException("User", "id", userId) },
    liveDemoLink = null
//    screenshots = listOf(),
//    relatedArticles = listOf()
)
