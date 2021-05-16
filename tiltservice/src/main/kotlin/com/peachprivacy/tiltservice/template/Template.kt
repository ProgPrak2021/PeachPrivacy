package com.peachprivacy.tiltservice.template

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
class Template {
    @Id
    @GeneratedValue
    var id: UUID? = null

    @ManyToOne
    @JoinColumn(name = "project_id")
    lateinit var project: Project

    var version: Int? = null

    var changelog: String? = null

    @ManyToMany
    @JoinTable(
        name = "template_inheritance",
        joinColumns = [JoinColumn(name = "child_id")],
        inverseJoinColumns = [JoinColumn(name = "parent_id")]
    )
    var parents: List<Template> = mutableListOf()

    @ManyToMany(mappedBy = "parents")
    var children: List<Template> = mutableListOf()

    var created: LocalDateTime = LocalDateTime.now()


    override fun toString(): String {
        return "Template(id=$id, template=$project, version=$version, changelog=$changelog, parents=$parents, children=$children, created=$created)"
    }
}