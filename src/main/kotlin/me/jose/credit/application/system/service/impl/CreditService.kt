package me.jose.credit.application.system.service.impl

import me.jose.credit.application.system.entity.Credit
import me.jose.credit.application.system.repository.CreditRepository
import me.jose.credit.application.system.service.ICreditServices
import org.springframework.stereotype.Service
import java.util.*
@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
): ICreditServices {
    override fun save(credit: Credit): Credit {
        // verificação se o costumer desse credito que esta tentando salva no banco de dados se ele existe
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }
    //antes de salva o crdito no banco de dados tem que fazer uma validação para saber se o customer que estamos vinculando com o esse credito se esta no banco de dados
    // ---------------------------------------------------------------
    //Essa função vai atraves de um id do customer vai tarzer uma lista relacioanada a esse costuemr
    override fun findALlByCustomer(customerId: Long): List<Credit> =
        this.creditRepository.findAllByCustomerId(customerId)
        // native query é interesante usar aqui para encontra todos os id veja mais sobre na documentação do spring




    // nesse vamos passa o credit code e vai volta o detalhamneto desse credit code
    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
       //
        val credit: Credit = (this.creditRepository.findByCreditCode(creditCode)
           ?: throw RuntimeException("Creditcode $creditCode not found")) // ?: elvis negocio essa função tem o obejtivo de verifica se o credit code tem o mesmo id de quem esta solicitanto para evita que eu, por exemplo, veja o dos outros
        return if (credit.customer?.id == customerId) credit else throw  RuntimeException("Contact admin")

    }
}