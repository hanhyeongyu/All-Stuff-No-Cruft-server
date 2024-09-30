package kata.practice.all_stuff_no_cruft.model

import kata.practice.all_stuff_no_cruft.Specification
import kata.practice.all_stuff_no_cruft.repository.ConferenceMemberRepository

class JoinedMemberSpecification(
    private val conferenceMemberRepository: ConferenceMemberRepository
): Specification<ConferenceMember>() {
    override fun isSatisfiedBy(candidate: ConferenceMember): Boolean {
        return conferenceMemberRepository.existsByConferenceIdAndUserId(candidate.conferenceId, candidate.userId)
    }

}