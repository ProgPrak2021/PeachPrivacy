package com.peachprivacy.tiltservice

import com.peachprivacy.tiltservice.template.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
class TemplateTest {
    @Autowired
    lateinit var templateRepository: TemplateRepository
    @Autowired
    lateinit var versionedTemplateRepository: VersionedTemplateRepository
    /*
    @Autowired
    lateinit var declaredClassRepository: DeclaredClassRepository
    @Autowired
    lateinit var declaredFieldRepository: DeclaredFieldRepository
    @Autowired
    lateinit var definedFieldRepository: DefinedFieldRepository
    */

    @Test
    fun dbRootProjectPersonTest() {
        val rootTemplate = templateRepository.save(Template().apply {
            name = "Root Template"
            baseDescription = "Root template defining basic types like text and number"
            detailedDescription = "Lorem ipsum blabla basic fields to define stuff bliblablub"
            authority = "public"
        })
        val rootTemplateV1 = versionedTemplateRepository.save(VersionedTemplate().apply {
            version = 1
            changelog = "Initial version"
            template = rootTemplate
        })
        /*
        val rootTextClass = declaredClassRepository.save(DeclaredClass().apply {
            name = "text"
            versionedTemplate = rootTemplateV1
        })
        val rootNumberClass = declaredClassRepository.save(DeclaredClass().apply {
            name = "number"
            versionedTemplate = rootTemplateV1
        })
        val rootProjectNameField = declaredFieldRepository.save(DeclaredField().apply {
            name = "projectName"
            description = "Name des Projektes"
            type = rootTextClass
            versionedTemplate = rootTemplateV1
        })
        val rootProjectVersionField = declaredFieldRepository.save(DeclaredField().apply {
            name = "projectVersion"
            description = "Version des Projektes"
            type = rootNumberClass
            versionedTemplate = rootTemplateV1
        })
        */

        val personTemplate = templateRepository.save(Template().apply {
            name = "Person Template"
            baseDescription = "Define a person!"
            authority = "public"
        })
        val personTemplateV1 = versionedTemplateRepository.save(VersionedTemplate().apply {
            template = personTemplate
            version = 1
            parents = listOf(rootTemplateV1)
        })
        /*
        val personNameField = declaredFieldRepository.save(DeclaredField().apply {
            name = "name"
            type = rootTextClass
            versionedTemplate = personTemplateV1
        })
        val personAgeField = declaredFieldRepository.save(DeclaredField().apply {
            name = "age"
            type = rootNumberClass
            versionedTemplate = personTemplateV1
        })
        val definedProjectField = definedFieldRepository.save(DefinedField().apply {
            field = rootProjectNameField
            value = "Person"
            versionedTemplate = personTemplateV1
        })
        */

        val personTemplateV2 = versionedTemplateRepository.save(VersionedTemplate().apply {
            template = personTemplate
            changelog = "I forgot to specify the project version!"
            version = 2
            parents = listOf(rootTemplateV1)
        })
        /*
        val personHeightField = declaredFieldRepository.save(DeclaredField().apply {
            name = "height"
            type = rootNumberClass
            versionedTemplate = personTemplateV1
        })
        val definedProjectVersionField = definedFieldRepository.save(DefinedField().apply {
            field = rootProjectVersionField
            value = "1"
            // Unterscheidet sich von der personTemplateV2.version.
            // Das hier ist nur (sinnloses) beispielfeld
            versionedTemplate = personTemplateV2
        })
         */

        val personImplTemplate = templateRepository.save(Template().apply {
            name = "Implemented Person"
            baseDescription = "My custom person!"
            detailedDescription = "I wanted to define a person while I went to the ice cream store and blablabla..."
            authority = "insertuserUUIDhere"
        })
        val personImplV1 = versionedTemplateRepository.save(VersionedTemplate().apply {
            version = 1
            template = personImplTemplate
            parents = mutableListOf(
                rootTemplateV1, // Muss eig nicht speziell angegeben werden - auch hier nur Beispiel für Mehrfacherben
                personTemplateV1
            )
        })
        /*
        val definedPersonNameField = definedFieldRepository.save(DefinedField().apply {
            field = personNameField
            value = "Patrick Hein"
            versionedTemplate = personImplV1
        })
        val definedPersonAgeField = definedFieldRepository.save(DefinedField().apply {
            field = personAgeField
            value = "21"
            versionedTemplate = personImplV1
        })
        val definedPersonHeightField = definedFieldRepository.save(DefinedField().apply {
            field = personHeightField
            value = "183cm"
            versionedTemplate = personImplV1
        })
         */

        /*templateRepository.getOne(personTemplate.id!!).apply {
            println(toString())
        }*/
    }
}