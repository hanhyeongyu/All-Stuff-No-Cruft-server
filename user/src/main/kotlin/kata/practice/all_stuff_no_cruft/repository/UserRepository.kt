package kata.practice.all_stuff_no_cruft.repository

import kata.practice.all_stuff_no_cruft.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User, Long>{
    fun findByEmail(email: String): Optional<User>
}