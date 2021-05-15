package com.peachprivacy.tiltservice.template

import java.util.*
import javax.persistence.*

@Entity
class DefinedField {
    @Id
    @GeneratedValue
    var id: UUID? = null

    @OneToOne  // Mehrfachvererbung?
    @JoinColumn(name = "field_id")
    lateinit var field: DeclaredField

    lateinit var value: String

    @ManyToOne
    @JoinColumn(name = "versioned_template_id")
    lateinit var versionedTemplate: VersionedTemplate


    override fun toString(): String {
        return "DefinedField(id=$id, field=$field, value='$value', versionedTemplate=$versionedTemplate)"
    }
}