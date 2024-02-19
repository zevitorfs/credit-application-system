package me.jose.credit.application.system.service.impl

import me.jose.credit.application.system.entity.Customer
import me.jose.credit.application.system.repository.CustomerRepository
import me.jose.credit.application.system.service.ICustomerServices
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
):ICustomerServices {
    override fun save(custoemr: Customer): Customer =
        this.customerRepository.save(custoemr)

// orelsethrow é para implementa uma exeção
    override fun findById(id: Long): Customer = this.customerRepository.findById(id).orElseThrow{
            throw RuntimeException("Id $id not found")
        }


    override fun delete(id: Long){
      this.customerRepository.deleteById(id)
    }

}

// Como fazemos para salva algo no banco de dados? vai ter que cahama a interface repository e uar o metodo de slava para salva no banco de dados
// O Update é feito no arquivo de controle