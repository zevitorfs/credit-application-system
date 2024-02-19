package me.jose.credit.application.system.repository

import jakarta.persistence.Id
import me.jose.credit.application.system.entity.Credit
import org.aspectj.apache.bcel.classfile.Code
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CreditRepository: JpaRepository <Credit, Long> {

   fun findByCreditCode(creditCode: UUID) : Credit?

   @Query(value = "SELECT * FROM CREDIT WHERE CREDIT_ID = ?1", nativeQuery = true)
   fun findAllByCustomerId(customerId: Long) :  List<Credit>
}