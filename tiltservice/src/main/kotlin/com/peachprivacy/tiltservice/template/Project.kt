package com.peachprivacy.tiltservice.template

import java.util.*
import javax.persistence.*

@Entity
class Project {
    @Id
    @GeneratedValue
    var id: UUID? = null

    lateinit var name: String

    var iconUrl: String? = null

    var baseDescription: String? = null

    var detailedDescription: String? = null

    @OneToMany(mappedBy = "template")
    var versions: List<Template> = mutableListOf()

    lateinit var authority: String


    override fun toString(): String {
        return "Project(id=$id, name='$name', iconUrl='$iconUrl', baseDescription=$baseDescription, detailedDescription=$detailedDescription, versions=$versions, authority='$authority')"
    }
}