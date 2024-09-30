package kata.practice.all_stuff_no_cruft.repository

import kata.practice.all_stuff_no_cruft.model.Media
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MediaRepository: JpaRepository<Media, Long> {
    fun findByMediaId(mediaId: UUID): Optional<Media>
    fun findByTalkId(talkId: Long): List<Media>
}