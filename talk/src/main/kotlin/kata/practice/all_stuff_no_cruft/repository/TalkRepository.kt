package kata.practice.all_stuff_no_cruft.repository

import kata.practice.all_stuff_no_cruft.model.Talk
import org.springframework.data.jpa.repository.JpaRepository

interface TalkRepository: JpaRepository<Talk, Long>