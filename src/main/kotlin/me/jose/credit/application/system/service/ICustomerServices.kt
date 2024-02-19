package me.jose.credit.application.system.service

import me.jose.credit.application.system.entity.Customer

interface ICustomerServices {
    fun save(custoemr: Customer): Customer
    fun findById(id: Long): Customer
    fun delete(id:Long)
}