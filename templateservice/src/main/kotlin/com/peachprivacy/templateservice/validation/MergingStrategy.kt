package com.peachprivacy.templateservice.validation

enum class MergingStrategy {
    NONE,       // No strategy was applied
    SINGLE,     // Equal values are reduced, only one value may remain
    ALL         // Applicable for arrays or objects - mostly applicable for arrays
}