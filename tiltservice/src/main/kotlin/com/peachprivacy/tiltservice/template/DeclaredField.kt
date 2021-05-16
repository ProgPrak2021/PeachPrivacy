package com.peachprivacy.tiltservice.template

import java.util.*
import javax.persistence.*

@Entity
class DeclaredField {
    @Id
    @GeneratedValue
    var id: UUID? = null

    lateinit var name: String

    var description: String? = null

    @ManyToOne
    @JoinColumn(name = "versioned_template_id")
    lateinit var versionedTemplate: VersionedTemplate

    @OneToOne  // Mehrfachvererbung?
    @JoinColumn(name = "class_id")
    lateinit var type: DeclaredClass


    override fun toString(): String {
        return "DeclaredField(id=$id, name='$name', description='$description', versionedTemplate=$versionedTemplate, type=$type)"
    }
}