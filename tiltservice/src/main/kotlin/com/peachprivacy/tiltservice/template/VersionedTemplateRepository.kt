package com.peachprivacy.tiltservice.template

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface VersionedTemplateRepository : JpaRepository<VersionedTemplate, UUID>