package com.peachprivacy.templateservice

import org.springframework.data.annotation.Id
import java.util.*

open class TemplateSchema(
    @Id
    var id: UUID? = null,
    var schema: String = ""
) {
    constructor() : this(null, "")
}