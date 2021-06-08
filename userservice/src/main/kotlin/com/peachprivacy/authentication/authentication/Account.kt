package com.peachprivacy.authentication.authentication

import javax.persistence.*

@Entity
@Table(name = "account")
class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Column(unique = true)
    lateinit var email: String

    lateinit var password: String

    lateinit var role: String

    @Column(nullable = true, unique = true)
    var emailToken: String? = null
}