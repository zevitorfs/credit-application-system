package me.jose.credit.application.system.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.impl.annotations.MockK
import me.jose.credit.application.system.entity.Credit
import me.jose.credit.application.system.entity.Customer
import me.jose.credit.application.system.entity.Endereco
import me.jose.credit.application.system.repository.CreditRepository
import me.jose.credit.application.system.repository.CustomerRepository
import me.jose.credit.application.system.service.CreditServiceTest
import org.aspectj.lang.annotation.After
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal
import java.net.URL
import java.time.LocalDate
import java.time.Month
import java.util.*

@SpringBootTest
@ActiveProfiles("test")
//Anotação para mockka as requisições
@AutoConfigureMockMvc
// Vai configura o contexto
@ContextConfiguration
class CreditResource {
    @Autowired
    private lateinit var creditRepository: CreditRepository
    private lateinit var customerRepository: CustomerRepository
    @Autowired
    private lateinit var mockMvc: MockMvc
    // Essa classe vai ajudar quando queremos passa um json na nossa string, tranforma nossa class em uma string para poder passa na requisção
    @Autowired private lateinit var objectMapper: ObjectMapper

    companion object{
        const val URL: String = "/api/customer"
    }

    @BeforeEach fun setup() = creditRepository.deleteAll()
    @AfterEach fun tearDown() = creditRepository.deleteAll()

    @Test
    fun `should create a customer and return 201 status` () {
        //given informações do dto
        val fakeCostumer: Customer = CreditServiceTest.buildCustomer()
        val creditDto: Credit =  buildCredit( customer = fakeCostumer)
        val valueAsString: String = objectMapper.writeValueAsString(creditDto)
        //When
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(valueAsString))
            //.andExpect(MockMvcResultMatchers.status().isCreated)
    }

    @Test
    fun `should not find customer wih invalid id and return 400 status`(){
        
    }

    @Test
    fun `Should find credit find all by customer 200 status`(){
        //given
        val fakeCostumer: Customer = CreditServiceTest.buildCustomer()
        val creditDto: Credit =  buildCredit( customer = fakeCostumer)
        val valueAsString: String = objectMapper.writeValueAsString(creditDto)

        //when
        mockMvc.perform(MockMvcRequestBuilders
            .get("$URL/${creditDto.id}")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }


    private fun buildCredit(
        creditValue: BigDecimal = BigDecimal.valueOf(500.0),
        dayFirstInstallment: LocalDate = LocalDate.of(2023, Month.APRIL, 22),
        numberOfInstallments: Int = 5,
        customer: Customer
    ): Credit = Credit(
        creditValue = creditValue,
        dayFirstInstallment = dayFirstInstallment,
        numberOFInstallments = numberOfInstallments,
        customer = customer
    )
    private fun buildCustomer(
        firstName: String = "Cami",
        lastName: String = "Cavalcante",
        cpf: String = "28475934625",
        email: String = "camila@gmail.com",
        password: String = "12345",
        zipCode: String = "12345",
        street: String = "Rua da Cami",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
    ) = Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        income = income,
        password = password,
        address = Endereco(
            zipCode = zipCode,
            street = street,
        )

    )
}