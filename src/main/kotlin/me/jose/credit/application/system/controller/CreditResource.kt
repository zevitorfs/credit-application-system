package me.jose.credit.application.system.controller

import me.jose.credit.application.system.dto.CreditDto
import me.jose.credit.application.system.dto.CreditView
import me.jose.credit.application.system.dto.CreditViewList
import me.jose.credit.application.system.entity.Credit
import me.jose.credit.application.system.service.impl.CreditService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditResource(
    // Como vamos trabalha com oresource, a camada de view que vai ter contato ccm o front-end
    //E depois vamos passa para a camada de serviço, vamos instancia ele
    private val creditService: CreditService

) {
    @PostMapping
    //Nessa função vamos passa um dto contendo as informaçõs para fazer esses salvamentos
    // Metodod para salva um credito no banco de dados
    fun saveCredit(@RequestBody creditDTO: CreditDto): ResponseEntity<String>{
        val credit : Credit = this.creditService.save(creditDTO.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body( "Credit ${credit.creditCode} - Customer ${credit.customer?.email} saved!!")
    }
    @GetMapping
    fun findAllByCostumerId(@RequestParam(value = "costumerId") costumerId: Long): ResponseEntity< List<CreditViewList>>{
        val creditViewList: List<CreditViewList> = this.creditService.findALlByCustomer(costumerId).stream().
        map { credit: Credit -> CreditViewList(credit) }.collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(creditViewList)
    }
    @GetMapping("/{creditCode}") // Toda vez que usar o PathVariable por essa anotação
    //Nessa função vamos passa dua informações o id do cliente e o credit code para fazer uma comparação e ter certeza que o o usuario
    fun findByCreditCode(@RequestParam(value = "costumerId") costumerId: Long,
                         @PathVariable creditCode: UUID): ResponseEntity<CreditView> {
        val credit = this.creditService.findByCreditCode(costumerId, creditCode)
        return ResponseEntity.status(HttpStatus.OK).body (CreditView(credit))
    }
}