package com.peachprivacy.tiltservice.template

import java.util.*
import javax.persistence.*

@Entity
class Template {
    @Id
    @GeneratedValue
    var id: UUID? = null

    lateinit var name: String

    @OneToMany(mappedBy = "template")
    var versions: List<VersionedTemplate> = mutableListOf()

    lateinit var authority: String
}