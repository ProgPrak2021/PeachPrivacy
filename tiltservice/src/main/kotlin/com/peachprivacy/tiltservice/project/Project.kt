package com.peachprivacy.tiltservice.project

import java.util.*
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
class Project {
    @Id
    @GeneratedValue
    var id: UUID? = null

    @Size(message = "Project name can't exceed 255 characters", max = 255)
    lateinit var name: String

    @Size(message = "Project icon URL can't exceed 255 characters", max = 255)
    var iconUrl: String? = null

    @Column(columnDefinition="TEXT")
    var baseDescription: String? = null

    @Column(columnDefinition="TEXT")
    var detailedDescription: String? = null

    @OneToMany(mappedBy = "project")
    var versions: List<Template> = mutableListOf()

    @Size(message = "Authority ID can't exceed 255 characters", max = 255)
    lateinit var authority: String


    override fun toString(): String {
        return "Project(id=$id, name='$name', iconUrl='$iconUrl', baseDescription=$baseDescription, detailedDescription=$detailedDescription, versions=$versions, authority='$authority')"
    }
}