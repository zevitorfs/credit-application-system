package me.jose.credit.application.system.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.jose.credit.application.system.entity.Customer
import me.jose.credit.application.system.entity.Endereco
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto (
   @field:NotEmpty(message = "Informação invalida") val firstName: String,
   @field:NotEmpty(message = "Informação invalida") val lastName: String,
   @field:NotEmpty(message = "Informação invalida")
   @field:CPF(message = "This invalid CPF") val cpf: String,
   @field:NotNull val income: BigDecimal,
   @field:NotEmpty(message = "Informação invalida")
   @field:Email(message = "Invalid email") val email: String,
   @field:NotEmpty(message = "Informação invalida") val password: String,
   @field:NotEmpty(message = "Informação invalida") val zipcode: String,
   @field:NotEmpty(message = "Informação invalida") val street: String
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


