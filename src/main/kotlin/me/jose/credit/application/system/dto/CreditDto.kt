package me.jose.credit.application.system.dto

import me.jose.credit.application.system.entity.Credit
import me.jose.credit.application.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    val creditValue: BigDecimal,
    val dayFirstOfInstallment: LocalDate,
    val numberOfIntallments: Int,
    val customerId: Long
) {
//Cria função que transforma o dto em um credit
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstOfInstallment,
        numberOFInstallments = this.numberOfIntallments,
        customer = Customer(id = this.customerId)

    )

}
