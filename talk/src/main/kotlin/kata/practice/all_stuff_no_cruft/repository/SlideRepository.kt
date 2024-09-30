package kata.practice.all_stuff_no_cruft.repository

import kata.practice.all_stuff_no_cruft.model.Slide
import org.springframework.data.jpa.repository.JpaRepository

interface SlideRepository: JpaRepository<Slide, Long>{
    fun findAllByTalkId(talkId: Long): List<Slide>
}