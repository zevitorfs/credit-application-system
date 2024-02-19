package me.jose.credit.application.system.dto

import me.jose.credit.application.system.entity.Customer
import me.jose.credit.application.system.entity.Endereco
import java.math.BigDecimal

data class CustomerDto (
    val firstName: String,
    val lastName: String,
    val cpf: String,
    val income: BigDecimal,
    val email: String,
    val password: String,
    val zipcode: String,
    val street: String
){
    // esse toEntity vai servi para monta o nosso customer para salva
    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName= this.lastName,
        cpf= this.cpf,
        income= this.income,
        email= this.email,
        password = this.password,
        address = Endereco(
            zipCode = this.zipcode,
            street = this.street
        )

        )
}


