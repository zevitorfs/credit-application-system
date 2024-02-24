package me.jose.credit.application.system.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import me.jose.credit.application.system.entity.Customer
import me.jose.credit.application.system.entity.Endereco
import me.jose.credit.application.system.exception.BusinessException
import me.jose.credit.application.system.repository.CustomerRepository
import me.jose.credit.application.system.service.impl.CustomerService
import org.aspectj.apache.bcel.util.Repository
import org.assertj.core.api.Assertions
import org.hibernate.service.Service
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.util.*
import kotlin.random.Random


@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerServiceTest {
    @MockK lateinit var customerRepository: CustomerRepository
    @InjectMockKs lateinit var customerService: CustomerService

    @Test
    fun `should create customer`(){
        //Given os dados que precsimaos receber, no caso um customer
        val fakeCostumer: Customer = buildCustomer()
        // vamos mackka o repository
        every { customerRepository.save(any()) } returns fakeCostumer

        //When aqui o método que precisamo testa no caso o save
        val actual: Customer = customerService.save(fakeCostumer)


        //Then a parti das acertivas
        // a função assertions não é a do jupiter
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCostumer)
        //Posso verifica se a função esta funcionando da maneira correta, nesse caso, se ela esta pegando so um custtomer
        verify (exactly = 1) { customerRepository.save(fakeCostumer) }
    }

    @Test
    fun `sould find By Id`(){
        //given
        val fakeId: Long = java.util.Random().nextLong()
        //fakeCostumer para que quando pesquisamos o fake customer pelo id a gente cosnigo recupera o fake customer
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        // Apos isso precismao mockka o findById que vai retorna um optionalCustomer do fake customer
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)


        //when
        val actual:Customer =  customerService.findById(fakeId)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Customer :: class.java)
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.findById(fakeId) }
    }
    @Test
    // Esse etste é para o caso o customer não existe
    fun `sould not find customer by invalid id and thorw BussinessExeception`() {
        //given
        val fakeId: Long = java.util.Random().nextLong()
        every { customerRepository.findById(fakeId) } returns Optional.empty()
        //when
        //then
        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { customerService.findById(fakeId) }
            // A messagem tem que ser a mesma que esta no customer service no método finbyid
            .withMessage("Id $fakeId not found")

    }



    // Par COMEÇA PRECISAMOS DE UM CUSTOMER ENTAO VAMOS CRIA UM BUILD QUE VAI DA UMA CUSTOMER FAKE
    companion object {
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