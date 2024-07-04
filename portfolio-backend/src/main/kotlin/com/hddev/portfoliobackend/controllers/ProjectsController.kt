package com.hddev.portfoliobackend.controllers

import com.hddev.portfoliobackend.auth.AdminRoleEvaluator
import com.hddev.portfoliobackend.model.ProjectDTO
import com.hddev.portfoliobackend.service.ProjectService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/projects")
class ProjectsController(
    private val projectService: ProjectService,
    private val adminRoleEvaluator: AdminRoleEvaluator
) {

    @GetMapping
    fun getAllProjects(): List<ProjectDTO> {
        val projects = projectService.getAllProjects()
        return projects
    }

    @GetMapping("/{id}")
    fun getProjectById(@PathVariable id: Long): ResponseEntity<ProjectDTO> {
        val project = projectService.getProjectById(id)
        return if (project != null) {
            ResponseEntity.ok(project)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createProject(@RequestBody projectDTO: ProjectDTO): ResponseEntity<ProjectDTO> {
        if (!adminRoleEvaluator.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
        val createdProject = projectService.createProject(projectDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject)
    }

    @PutMapping("/{id}")
    fun updateProject(@PathVariable id: Long, @RequestBody projectDTO: ProjectDTO): ResponseEntity<ProjectDTO> {
        if (!adminRoleEvaluator.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
        val updatedProject = projectService.updateProject(id, projectDTO)
        return if (updatedProject != null) {
            ResponseEntity.ok(updatedProject)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteProject(@PathVariable id: Long): ResponseEntity<Void> {
        if (!adminRoleEvaluator.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
        val deleted = projectService.deleteProject(id)
        return if (deleted) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}