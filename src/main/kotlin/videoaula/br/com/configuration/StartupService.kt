package videoaula.br.com.configuration

import io.micronaut.context.event.StartupEvent
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import videoaula.br.com.model.User
import videoaula.br.com.repository.UserRepository

@Singleton
class StartupService(private val userRepository: UserRepository) {

    private val log = LoggerFactory.getLogger(StartupService::class.java)

    @EventListener
    fun onStartupEvent(event: StartupEvent) {
        val user = userRepository.saveEncoded((User("43235435480", "1234")))
        log.info("User added: {}", user)
    }
}