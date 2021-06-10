package com.peachprivacy.templateservice.template

import com.peachprivacy.templateservice.TemplateSchema
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface TemplateSchemaRepository : MongoRepository<TemplateSchema, UUID>