package com.peachprivacy.templateservice

import com.google.common.collect.Maps
import com.peachprivacy.templateservice.resolve.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.Resource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.util.AssertionErrors.assertEquals
import org.springframework.test.util.AssertionErrors.assertTrue
import org.springframework.util.FileCopyUtils
import java.io.InputStreamReader

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
class BasicSchemaResolvingTest {
    private fun resolveTwoSchemas(
        validationService: ValidationService,
        resource1: Resource,
        resource2: Resource
    ): Map<String, HandledConflict<*>> {
        val declaringSchema = with(InputStreamReader(resource1.inputStream)) {
            FileCopyUtils.copyToString(this)
        }
        val definingSchema = with(InputStreamReader(resource2.inputStream)) {
            FileCopyUtils.copyToString(this)
        }

        val declaringPointers = validationService.getResolvedJsonPointers(declaringSchema)
        val definingPointers = validationService.getResolvedJsonPointers(definingSchema)

        return validationService.mergeResolvedJsonPointers(listOf(declaringPointers, definingPointers))
    }

    @Test
    fun fullyResolveDeclaringWithOneDefiningSchema(
        @Value("classpath:schema/basic/double/simple-declaring.json") declaringSchemaResource: Resource,
        @Value("classpath:schema/basic/double/simple-defining.json") definingSchemaResource: Resource,
        @Autowired validationService: ValidationService
    ) {
        val mergedResolvedPointers = resolveTwoSchemas(validationService, declaringSchemaResource, definingSchemaResource)

        assertTrue(
            "Only JSON Pointer for foo property in schema equals false without conflict",
            Maps.difference(
                mapOf("/properties/foo" to ResolvedConflict(mutableListOf("false"), valueHolder = "false")),
                mergedResolvedPointers
            ).areEqual()
        )

        println()
        println(mergedResolvedPointers)
        println()
    }

    @Test
    fun resolveWithConflictDeclaringWithOneDefiningSchema(
        @Value("classpath:schema/basic/double/conflict-resolvable-a.json") declaringSchemaResource: Resource,
        @Value("classpath:schema/basic/double/conflict-resolvable-b.json") definingSchemaResource: Resource,
        @Autowired validationService: ValidationService
    ) {
        val mergedResolvedPointers = resolveTwoSchemas(validationService, declaringSchemaResource, definingSchemaResource)

        assertEquals(
            "Only JSON Pointer for foo property in schema equals true with conflict - " +
                    "resolvable since equal values",
            mapOf("/properties/foo" to ResolvedConflict(mutableListOf("true"), valueHolder = "true")),
            mergedResolvedPointers
        )

        println()
        println(mergedResolvedPointers)
        println()
    }

    @Test
    fun resolveFailsWithConflictDeclaringWithOneDefiningSchema(
        @Value("classpath:schema/basic/double/conflict-unresolvable-a.json") declaringSchemaResource: Resource,
        @Value("classpath:schema/basic/double/conflict-unresolvable-b.json") definingSchemaResource: Resource,
        @Autowired validationService: ValidationService
    ) {
        val mergedResolvedPointers = resolveTwoSchemas(validationService, declaringSchemaResource, definingSchemaResource)

        assertEquals(
            "Only JSON Pointer for foo property in schema fails to resolve because of two non-mergeable values",
            mapOf("/properties/foo" to UnresolvedConflict(mutableListOf("true", "false"))),
            mergedResolvedPointers
        )

        println()
        println(mergedResolvedPointers)
        println()
    }
}