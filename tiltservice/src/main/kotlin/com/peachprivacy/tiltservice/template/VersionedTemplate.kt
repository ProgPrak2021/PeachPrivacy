package com.peachprivacy.tiltservice.template

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
class VersionedTemplate {
    @Id
    @GeneratedValue
    var id: UUID? = null

    @ManyToOne
    @JoinColumn(name = "template_id")
    lateinit var template: Template

    var version: Int? = null

    @ManyToMany
    @JoinTable(
        name = "template_inheritance",
        joinColumns = [JoinColumn(name = "child_id")],
        inverseJoinColumns = [JoinColumn(name = "parent_id")]
    )
    var parents: List<VersionedTemplate> = mutableListOf()

    @ManyToMany(mappedBy = "parents")
    var children: List<VersionedTemplate> = mutableListOf()

    var created: LocalDateTime = LocalDateTime.now()


}