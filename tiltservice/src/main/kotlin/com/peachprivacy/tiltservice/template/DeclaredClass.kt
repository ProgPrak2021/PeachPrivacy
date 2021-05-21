package com.peachprivacy.tiltservice.template

import java.util.*
import javax.persistence.*

@Entity
class DeclaredClass {
    @Id
    @GeneratedValue
    var id: UUID? = null

    lateinit var name: String

    @ManyToOne
    @JoinColumn(name = "versioned_template_id")
    lateinit var versionedTemplate: VersionedTemplate


    override fun toString(): String {
        return "DeclaredClass(id=$id, name='$name', versionedTemplate=$versionedTemplate)"
    }
}