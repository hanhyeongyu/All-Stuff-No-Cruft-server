package kata.practice.all_stuff_no_cruft.projection

import kata.practice.all_stuff_no_cruft.model.User
import kata.practice.all_stuff_no_cruft.repository.UserRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
internal class UserJpaReaModel(
    private val memberRepository: UserRepository
): UserReadModel {

    override fun user(email: String): User? {
        return memberRepository.findByEmail(email)
            .getOrNull()
    }
}