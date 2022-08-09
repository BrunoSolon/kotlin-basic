package videoaula.br.com.repository

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import org.springframework.security.crypto.password.PasswordEncoder
import videoaula.br.com.model.User

@Repository
abstract class UserRepository(private val passwordEncoder: PasswordEncoder): JpaRepository<User, String> {
    fun saveEncoded(entity: User): User{
        return save(User(cpf = entity.cpf, password = passwordEncoder.encode(entity.password)))
    }
}