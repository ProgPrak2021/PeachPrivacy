package com.peachprivacy.templateservice.resolve

data class ValueDefinitionItem<T>(
    val path: List<String>,
    val question: String,
    val description: String,
    val type: String,
    var definition: ValueAttempt<T>?
)