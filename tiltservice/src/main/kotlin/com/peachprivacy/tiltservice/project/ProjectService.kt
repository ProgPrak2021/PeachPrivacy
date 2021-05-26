package com.peachprivacy.tiltservice.project

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProjectService @Autowired constructor(
    private val projectRepository: ProjectRepository
) {
    fun get(id: UUID): Project? {
        return projectRepository.findByIdOrNull(id)
    }

    fun update(project: Project): Project {
        return projectRepository.save(project)
    }

    fun create(project: Project): UUID {
        return projectRepository.save(project).id!!
    }

    fun delete(id: UUID) {
        return projectRepository.deleteById(id)
    }
}