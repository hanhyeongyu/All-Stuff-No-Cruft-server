package kata.practice.all_stuff_no_cruft.repository

import kata.practice.all_stuff_no_cruft.model.ConferenceMember
import org.springframework.data.jpa.repository.JpaRepository

interface ConferenceMemberRepository: JpaRepository<ConferenceMember, Long>{
    fun existsByConferenceIdAndUserId(conferenceId: Long, userId: Long): Boolean
}