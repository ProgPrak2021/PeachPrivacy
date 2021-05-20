package com.peachprivacy.tiltservice.template

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface DeclaredFieldRepository : JpaRepository<DeclaredField, UUID>