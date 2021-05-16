package com.peachprivacy.tiltservice.template

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface DefinedFieldRepository : JpaRepository<DefinedField, UUID>