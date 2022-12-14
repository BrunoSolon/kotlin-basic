package videoaula.br.com.model

import io.micronaut.core.annotation.Introspected
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.Size

@Entity
@Introspected
data class User(
    @Id
    val cpf: String,

    @Column
    @Size(min = 6)
    val password: String
)
