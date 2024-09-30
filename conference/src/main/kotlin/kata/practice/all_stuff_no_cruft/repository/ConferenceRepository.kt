package kata.practice.all_stuff_no_cruft.repository

import kata.practice.all_stuff_no_cruft.model.Conference
import org.springframework.data.jpa.repository.JpaRepository

interface ConferenceRepository: JpaRepository<Conference, Long>