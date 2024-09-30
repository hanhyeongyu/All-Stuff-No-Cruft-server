package kata.practice.all_stuff_no_cruft.repository

import kata.practice.all_stuff_no_cruft.model.Speaker
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SpeakerRepository: JpaRepository<Speaker, Long>{
    fun findByEmail(email: String): Optional<Speaker>
}