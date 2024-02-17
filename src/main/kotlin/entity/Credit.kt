package entity

//import org.hibernate.validator.constraints.UUID
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID
@Entity
@Table(name = "Credit")
data class Credit(
    @Column(nullable = false, unique = true) val creditCode: UUID = UUID.randomUUID(),
    @Column(nullable = false) val creditValue: BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false) val dayFirstInstallment: LocalDate,
    @Column(nullable = false) val numberOFInstallments: Int=0,
    // O campo status é um enum entao usar esse anotaçaõ
    @Enumerated val status: Status = Status.IN_PROGESS,
    @ManyToOne val customer: Customer? = null,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null

)
