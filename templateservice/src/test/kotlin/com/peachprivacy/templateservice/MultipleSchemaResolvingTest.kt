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
import org.springframework.test.util.AssertionErrors.assertTrue
import org.springframework.util.FileCopyUtils
import java.io.InputStreamReader

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
class MultipleSchemaResolvingTest {
    private fun resolveMultipleSchemas(
        validationService: ValidationService,
        vararg resources: Resource
    ): Map<String, HandledConflict<*>> {
        val resolvedPointers = resources.map {
            with(InputStreamReader(it.inputStream)) {
                FileCopyUtils.copyToString(this)
            }
        }.map {
            validationService.getResolvedJsonPointers(it)
        }

        return validationService.mergeResolvedJsonPointers(resolvedPointers)
    }

    @Test
    fun fullyResolveDeclaringWithOneDefiningSchema(
        @Value("classpath:schema/basic/multiple/simple-a.json") schemaResource1: Resource,
        @Value("classpath:schema/basic/multiple/simple-b.json") schemaResource2: Resource,
        @Value("classpath:schema/basic/multiple/simple-c.json") schemaResource3: Resource,
        @Autowired validationService: ValidationService
    ) {
        val mergedResolvedPointers = resolveMultipleSchemas(validationService,
            schemaResource1, schemaResource2, schemaResource3
        )

        assertTrue(
            "Multiple Schemas are capable of defining each others values",
            Maps.difference(
                mapOf(
                    "/properties/foo" to ResolvedConflict(mutableListOf("Define for declared in schema A"), valueHolder = "Define for declared in schema A"),
                    "/properties/bar" to ResolvedConflict(mutableListOf("Define for declared in schema A"), valueHolder = "Define for declared in schema A"),
                    "/properties/baz" to ResolvedConflict(mutableListOf("Define for declared in schema B"), valueHolder = "Define for declared in schema B"),
                    "/properties/foobar/properties/abc" to ResolvedConflict(mutableListOf("42"), valueHolder = "42"),
                ),
                mergedResolvedPointers
            ).areEqual()
        )

        println()
        println(validationService.getResolvedObject(mergedResolvedPointers))
        println()
        println(mergedResolvedPointers)
        println()
    }
}