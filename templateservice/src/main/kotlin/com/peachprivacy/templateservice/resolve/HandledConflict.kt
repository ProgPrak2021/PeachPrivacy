package com.peachprivacy.templateservice.resolve

import java.util.*

/*sealed class HandledConflict<T>(
    open val schema: UUID?
)

data class UnresolvedConflict<T>(
    override val schema: UUID?,
    val candidates: MutableList<HandledConflict<T>> = mutableListOf()
): HandledConflict<T>(schema)

sealed class ResolvedConflict<T>(
    override val schema: UUID?,
    override val candidates: MutableList<HandledConflict<T>> = mutableListOf(),
    open val merge: SelfMergingStrategy = SelfMergingStrategy.SINGLE,
    open val value: T?
): HandledConflict<T>(schema, candidates, merge)

data class OverridingConflict<T>(
    override val schema: UUID?,
    val parentCandidates: MutableList<HandledConflict<T>> = mutableListOf(),
    override val value: T?
): ResolvedConflict<T>(schema, candidates, merge, value)

data class MergedConflict<T>(

    override val candidates: MutableList<HandledConflict<T>> = mutableListOf(),
)*/

sealed class ValueAttempt<T>(
    open val schema: UUID
)
interface ValueMergeConflict<T> {
    val schema: UUID
    val candidates: List<ValueDefinitionItem<T>>
    val appliedStrategy: MergingStrategy
}

data class FailedMergeValueDefinition<T>(
    override val schema: UUID,
    override val candidates: MutableList<ValueDefinitionItem<T>>,
    override val appliedStrategy: MergingStrategy
) : ValueAttempt<T>(schema), ValueMergeConflict<T> {
    val relevantSurfaceCandidates: List<SuccessfulValueDefinition<T>> by lazy {
        val result = mutableListOf<SuccessfulValueDefinition<T>>()
        val candidatesQueue = mutableListOf<ValueAttempt<T>>(this)
        var queueIndex = 0

        while (queueIndex < candidatesQueue.size) {
            when (val candidate = candidatesQueue[queueIndex]) {
                is SuccessfulValueDefinition<*> ->
                    result.add(candidate as SuccessfulValueDefinition<T>)
                is FailedMergeValueDefinition<*> ->
                    candidatesQueue.addAll(candidate.candidates.map { it as ValueAttempt<T> })
            }

            queueIndex++
        }

        return@lazy result
    }
}

sealed class SuccessfulValueDefinition<T>(
    override val schema: UUID,
    open val preferredMergingStrategy: MergingStrategy,
    open val value: T?
) : ValueAttempt<T>(schema)
data class MergedValueDefinition<T>(
    override val schema: UUID,
    override val candidates: MutableList<ValueDefinitionItem<T>>,
    override val preferredMergingStrategy: MergingStrategy,
    override val appliedStrategy: MergingStrategy,
    override val value: T?
) : SuccessfulValueDefinition<T>(schema, preferredMergingStrategy, value), ValueMergeConflict<T>
data class OverridingValueDefinition<T>(
    override val schema: UUID,
    override val preferredMergingStrategy: MergingStrategy,
    val parent: ValueAttempt<T>,
    override val value: T?
) : SuccessfulValueDefinition<T>(schema, preferredMergingStrategy, value)
data class SimpleValueDefinition<T>(
    override val schema: UUID,
    override val preferredMergingStrategy: MergingStrategy,
    override val value: T?
) : SuccessfulValueDefinition<T>(schema, preferredMergingStrategy, value)
