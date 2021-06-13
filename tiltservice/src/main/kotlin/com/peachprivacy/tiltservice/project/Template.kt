package com.peachprivacy.tiltservice.project

import com.fasterxml.jackson.annotation.*
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@JsonIdentityInfo(scope = Template::class, generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
class Template {
    @Id
    @GeneratedValue
    var id: UUID? = null

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIdentityReference(alwaysAsId = true)
    lateinit var project: Project

    var version: Int? = null

    @Column(columnDefinition="TEXT")
    var changelog: String? = null

    @ManyToMany
    @JoinTable(
        name = "template_inheritance",
        joinColumns = [JoinColumn(name = "child_id")],
        inverseJoinColumns = [JoinColumn(name = "parent_id")]
    )
    @JsonIdentityReference(alwaysAsId = true)
    var parents: List<Template> = mutableListOf()

    @ManyToMany(mappedBy = "parents")
    @JsonIdentityReference(alwaysAsId = true)
    var children: List<Template> = mutableListOf()

    var created: LocalDateTime = LocalDateTime.now()

    @JsonInclude
    @Transient
    var schema: String = ""


    override fun toString(): String {
        return "Template(id=$id, template=$project, version=$version, changelog=$changelog, parents=$parents, children=$children, created=$created)"
    }
}