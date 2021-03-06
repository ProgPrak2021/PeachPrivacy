package com.peachprivacy.tiltservice.project

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProjectService @Autowired constructor(
    private val projectRepository: ProjectRepository
) {
    fun getAll(authorities: List<String>): List<Project> {
        return projectRepository.findAll().filter { it.authority in authorities }
    }

    fun getRandom(amount: Int): List<Project> {
        return projectRepository.findAll().shuffled().take(amount)
    }

    fun get(id: UUID): Project? {
        return projectRepository.findByIdOrNull(id)
    }

    fun update(project: Project): Project {
        return projectRepository.save(project)
    }

    fun create(project: Project): Project {
        return projectRepository.save(project)
    }

    fun delete(id: UUID) {
        return projectRepository.deleteById(id)
    }
}