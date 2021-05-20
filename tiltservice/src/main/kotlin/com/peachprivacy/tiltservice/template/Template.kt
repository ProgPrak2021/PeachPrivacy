package com.peachprivacy.tiltservice.template

import java.util.*
import javax.persistence.*

@Entity
class Template {
    @Id
    @GeneratedValue
    var id: UUID? = null

    lateinit var name: String

    var baseDescription: String? = null

    var detailedDescription: String? = null

    @OneToMany(mappedBy = "template")
    var versions: List<VersionedTemplate> = mutableListOf()

    lateinit var authority: String


    override fun toString(): String {
        return "Template(id=$id, name='$name', baseDescription=$baseDescription, detailedDescription=$detailedDescription, versions=$versions, authority='$authority')"
    }
}