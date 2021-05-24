package com.peachprivacy.tiltservice.project

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TemplateRepository : JpaRepository<Template, UUID>