package kata.practice.all_stuff_no_cruft.repository

import kata.practice.all_stuff_no_cruft.model.ProfileImage
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ProfileImageRepository: JpaRepository<ProfileImage, Long>{
    fun findByUserId(userId: Long): Optional<ProfileImage>
}