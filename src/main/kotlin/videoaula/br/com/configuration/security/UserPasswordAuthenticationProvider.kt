package videoaula.br.com.configuration.security

import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.*
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import org.springframework.security.crypto.password.PasswordEncoder
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import videoaula.br.com.repository.UserRepository

@Singleton
class UserPasswordAuthenticationProvider(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder): AuthenticationProvider {
    override fun authenticate(httpRequest: HttpRequest<*>?, authenticationRequest: AuthenticationRequest<*, *>): Publisher<AuthenticationResponse> {
        val user = userRepository.findById(authenticationRequest.identity as String)

        return Flux.create({ emitter: FluxSink<AuthenticationResponse> ->
            if (user.isPresent) {
                if (passwordEncoder.matches(authenticationRequest.secret as String, user.get().password)) {
                    emitter.next(AuthenticationResponse.success(user.get().cpf, listOf()))
                    emitter.complete()
                } else {
                    emitter.next(AuthenticationResponse.failure(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH))
                }
            } else {
                emitter.error(AuthenticationResponse.exception())
            }
        }, FluxSink.OverflowStrategy.ERROR)
    }
}