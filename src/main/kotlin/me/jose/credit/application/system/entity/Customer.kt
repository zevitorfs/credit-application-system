package me.jose.credit.application.system.entity

import jakarta.persistence.*
import java.math.BigDecimal

//import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address

@Entity
@Table(name = "Cliente")
data class Customer(
    @Column(nullable = false) var firstName: String = "",
    @Column(nullable = false) var lastName: String = "",
    @Column(nullable = false, unique = true) var cpf: String = "",
    @Column(nullable = false, unique = true) var email: String = "",
    @Column(nullable = false) var password: String = "",
    @Column(nullable = false) var income: BigDecimal = BigDecimal.ZERO,
    // A entidade Endereco a intenção não criar uma tabela é emenda com a entidade customer com a anotação embedded
    @Column(nullable = false) @Embedded var address: Endereco = Endereco(),
    // Essa linha abaixo é o que vai permite a relação entre as entidades vai ser tipo a chave estrangeira
    // fetch
    // cascade
    // mappedBy que serve para referencia o credit
    @Column(nullable = false) @OneToMany(fetch = FetchType.LAZY,
        cascade = arrayOf(CascadeType.REMOVE, CascadeType.PERSIST),
        mappedBy = "customer")

    var credits: List<Credit> = mutableListOf(),
    // para gera o id automaticamente
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null
)
