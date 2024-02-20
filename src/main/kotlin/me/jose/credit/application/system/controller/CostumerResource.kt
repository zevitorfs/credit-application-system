package me.jose.credit.application.system.controller

import jakarta.validation.Valid
import me.jose.credit.application.system.dto.CustomerDto
import me.jose.credit.application.system.dto.CustomerUpdateDto
import me.jose.credit.application.system.dto.CustomerView
import me.jose.credit.application.system.entity.Customer
import me.jose.credit.application.system.service.impl.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
//Indica a url que vai chama essa classe
@RequestMapping("/api/customer")
class CostumerResource(
    private val customerService: CustomerService
){
    @PostMapping
    // Essa anotação indica que é para salva no banco de dados
    fun salveCustomer(@RequestBody @Valid  customerDTO: CustomerDto): ResponseEntity<String> {
        val savedCustomer = this.customerService.save(customerDTO.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer ${savedCustomer.email} saved!")

    }
    @GetMapping("/{id}")
    // Patchvariable vai vim la do json que vao manda a url ele vai pega o id e manda para o service que vai implementa o negocio
    fun findById(@PathVariable id:Long): ResponseEntity<CustomerView> {
        // a val varivel retorna um customer que o id quer
       val customer : Customer =  this.customerService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customer))
    }
    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long) = this.customerService.delete(id)

    @PatchMapping
    //
    fun updateCustomer(@RequestParam(value = "customerId") id: Long,
                       @RequestBody @Valid customerUpdateDto: CustomerUpdateDto): ResponseEntity<CustomerView>{
        val customer: Customer = this.customerService.findById(id)
        val customertoUpdate: Customer = customerUpdateDto.toEntity(customer)
        val customerupdated: Customer =  this.customerService.save(customertoUpdate)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customerupdated))

    }
}