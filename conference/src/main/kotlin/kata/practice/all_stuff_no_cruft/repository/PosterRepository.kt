package kata.practice.all_stuff_no_cruft.repository

import kata.practice.all_stuff_no_cruft.model.Poster
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PosterRepository: JpaRepository<Poster, Long>{
    fun findByConferenceId(conferenceId: Long): Optional<Poster>
}