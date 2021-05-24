package com.peachprivacy.templateservice.validation

sealed class HandledConflict<T>(
    open val candidates: MutableList<T> = mutableListOf(),
    open val merge: MergingStrategy
)

data class UnresolvedConflict<T>(
    override val candidates: MutableList<T> = mutableListOf()
): HandledConflict<T>(candidates, MergingStrategy.NONE)

data class ResolvedConflict<T>(
    override val candidates: MutableList<T> = mutableListOf(),
    override val merge: MergingStrategy = MergingStrategy.SINGLE,
    val value: T?
): HandledConflict<T>(candidates, merge)