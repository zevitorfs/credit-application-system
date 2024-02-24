package me.jose.credit.application.system.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import me.jose.credit.application.system.entity.Credit
import me.jose.credit.application.system.entity.Customer
import me.jose.credit.application.system.entity.Endereco
import me.jose.credit.application.system.repository.CreditRepository
import me.jose.credit.application.system.repository.CustomerRepository
import me.jose.credit.application.system.service.impl.CreditService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Month
import java.util.*


@ExtendWith(MockKExtension:: class)
@SpringBootTest
class CreditServiceTest {

    @MockK
    private lateinit var creditRepository: CreditRepository
    private  lateinit var customerRepository: CustomerRepository
    @InjectMockKs lateinit var creditService: CreditService

    @Test
    fun`should create credit`(){
        //Given
        val fakeCostumer: Customer = buildCustomer()
        val fakeCredit: Credit = buildCredit( customer = fakeCostumer)
        every { creditRepository.save(fakeCredit) } returns fakeCredit
        //When
        val actual: Credit = creditService.save(fakeCredit)

        //Then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCredit)
        verify (exactly = 1) { creditRepository.save(fakeCredit) }
    }


    companion object {
        fun buildCredit(
        creditValue: BigDecimal = BigDecimal.valueOf(500.0),
        dayFirstInstallment: LocalDate = LocalDate.of(2023, Month.APRIL, 22),
        numberOfInstallments: Int = 5,
        customer: Customer,
        id: Long = 1L
        ): Credit = Credit(
        creditValue = creditValue,
        dayFirstInstallment = dayFirstInstallment,
        numberOFInstallments = numberOfInstallments,
        customer = buildCustomer(),
            id = id
        )
        fun buildCustomer(
            firstName: String = "Cami",
            lastName: String = "Cavalcante",
            cpf: String = "28475934625",
            email: String = "camila@gmail.com",
            password: String = "12345",
            zipCode: String = "12345",
            street: String = "Rua da Cami",
            income: BigDecimal = BigDecimal.valueOf(1000.0),
            id: Long = 1L
        ) = Customer(
            firstName = firstName,
            lastName = lastName,
            cpf = cpf,
            email = email,
            password = password,
            address = Endereco(
                zipCode = zipCode,
                street = street,
            ),
            income = income,
            id = id
        )
    }
}