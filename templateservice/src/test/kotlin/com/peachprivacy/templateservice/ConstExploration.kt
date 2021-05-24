package com.peachprivacy.templateservice

import Tilt
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.worldturner.medeia.api.jackson.MedeiaJacksonApi
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.Resource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.util.FileCopyUtils
import java.io.InputStreamReader

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
class ConstExploration {
    @Test
    fun sampleTiltJSONSuccessfullyPassesMedeiaValidation(
        @Autowired api: MedeiaJacksonApi,
        @Value("classpath:tilt-schema-632d064.json") tiltSchemaResource: Resource,
        @Value("classpath:valid-tilt-1.json") testResource: Resource,
        @Autowired objectMapper: ObjectMapper
    ) {
        val tiltSchema = with(InputStreamReader(tiltSchemaResource.inputStream)) {
            FileCopyUtils.copyToString(this)
        }
        val testParser = objectMapper.createParser(testResource.inputStream)

        val template = Template(api, tiltSchema)

        val validatedParser = api.decorateJsonParser(template.validator, testParser)

        val tilt = objectMapper.readValue<Tilt>(validatedParser)
    }
}