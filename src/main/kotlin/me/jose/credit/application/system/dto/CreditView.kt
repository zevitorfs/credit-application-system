package me.jose.credit.application.system.dto

import me.jose.credit.application.system.entity.Credit
import me.jose.credit.application.system.entity.Status
import java.math.BigDecimal
import java.util.UUID

data class CreditView (
    val creditCode: UUID,
    val creditValue: BigDecimal,
    val numberOfInstallments: Int,
    val status: Status,
    val emailCustomer: String?,
    val incomeCustomer: BigDecimal?


){
    constructor(credit: Credit): this(
        creditCode = credit.creditCode,
        creditValue = credit.creditValue,
        numberOfInstallments = credit.numberOFInstallments,
        status = credit.status,
        emailCustomer = credit.customer?.email,
        incomeCustomer = credit.customer?.income
    )

}
