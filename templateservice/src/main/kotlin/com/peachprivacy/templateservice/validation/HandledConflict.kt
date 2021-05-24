package com.peachprivacy.templateservice.validation

sealed class HandledConflict<T>(
    open val originalValues: MutableList<T> = mutableListOf(),
    open val mergingStrategy: MergingStrategy
)

data class UnresolvedConflict<T>(
    override val originalValues: MutableList<T> = mutableListOf()
): HandledConflict<T>(originalValues, MergingStrategy.NONE)

data class ResolvedConflict<T>(
    override val originalValues: MutableList<T> = mutableListOf(),
    override val mergingStrategy: MergingStrategy = MergingStrategy.SINGLE,
    val value: T?
): HandledConflict<T>(originalValues, mergingStrategy)