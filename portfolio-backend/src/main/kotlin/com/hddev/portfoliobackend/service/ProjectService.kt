package com.hddev.portfoliobackend.service

import com.hddev.portfoliobackend.repository.ProjectRepository
import com.hddev.portfoliobackend.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class ProjectService(
    private val projectRepository: ProjectRepository,
    private val userRepository: UserRepository
) {
    fun getAllProjects(): List<ProjectDTO> =
        projectRepository.findAll().map { it.toDTO() }

    fun getProjectById(id: Long): ProjectDTO =
        projectRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Project", "id", id) }
            .toDTO()

    fun createProject(projectDTO: ProjectDTO, userId: Long): ProjectDTO {
        val user = userRepository.findById(userId).orElseThrow { ResourceNotFoundException("User", "id", userId) }
        val project = projectDTO.toEntity(user)
        return projectRepository.save(project).toDTO()
    }

    fun updateProject(id: Long, projectDTO: ProjectDTO): ProjectDTO {
        val project = projectRepository.findById(id).orElseThrow { ResourceNotFoundException("Project", "id", id) }
        project.update(projectDTO)
        return projectRepository.save(project).toDTO()
    }

    fun deleteProject(id: Long) {
        projectRepository.deleteById(id)
    }
}
