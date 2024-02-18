package me.jose.credit.application.system.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded

@Embeddable
data class Endereco(
    @Column(nullable = false) var zipCode: String = "",
    @Column(nullable = false) var street: String = ""
)


