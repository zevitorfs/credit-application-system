package me.jose.credit.application.system.service

import me.jose.credit.application.system.entity.Credit
import java.util.UUID

interface ICreditServices {
    fun save(credit: Credit): Credit
    // com essa função (findAllByCustomer) ela vai receber um customer  e com isso essa função vai lista todas as solicitações de credito a parti dele
    fun findALlByCustomer(customerId: Long): List<Credit>

    //Essa função vai encontra um credito de acordo com o id de u mcredito
    fun findByCreditCode(customerId: Long, creditCode: UUID): Credit
}
