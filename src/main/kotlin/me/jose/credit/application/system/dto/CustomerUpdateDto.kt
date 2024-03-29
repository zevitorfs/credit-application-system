package me.jose.credit.application.system.dto

import jakarta.validation.constraints.NotEmpty
import me.jose.credit.application.system.entity.Customer
import java.math.BigDecimal

data class CustomerUpdateDto(
    @field:NotEmpty(message = "Informação invalida") val firstName: String,
    @field:NotEmpty(message = "Informação invalida") val lastName: String,
    @field:NotEmpty(message = "Informação invalida")val income: BigDecimal,
    @field:NotEmpty(message = "Informação invalida") val zipCode: String,
    @field:NotEmpty(message = "Informação invalida") val street: String
) {
    // essa função vai funciona como? ela vai pega um customer que quer atualizar e vai atualiza ele atravez dos dados que recebemos do customerUpdate
    fun toEntity(customer: Customer): Customer{
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.street = this.street
        customer.address.zipCode = this.zipCode
        return customer
    }
}
