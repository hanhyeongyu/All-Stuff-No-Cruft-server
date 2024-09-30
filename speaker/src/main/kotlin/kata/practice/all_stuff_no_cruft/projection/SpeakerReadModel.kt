package kata.practice.all_stuff_no_cruft.projection

import kata.practice.all_stuff_no_cruft.common.exception.EntityNotFoundException
import kata.practice.all_stuff_no_cruft.model.Speaker
import kata.practice.all_stuff_no_cruft.projection.viewmodel.SpeakerViewModel
import kata.practice.all_stuff_no_cruft.repository.SpeakerRepository
import kata.practice.all_stuff_no_cruft.service.ProfileImageService
import org.springframework.stereotype.Service
import java.security.Principal
import kotlin.jvm.optionals.getOrNull

@Service
class SpeakerReadModel(
    private val speakerRepository: SpeakerRepository,
    private val profileImageService: ProfileImageService
){

    fun speaker(email: String): Speaker? {
        return speakerRepository.findByEmail(email)
            .getOrNull()
    }

    fun profile(principal: Principal): SpeakerViewModel{
        val id = principal.name.toLong()
        val speaker =  speakerRepository.findById(id)
            .getOrNull() ?: throw  EntityNotFoundException()

        val result = SpeakerViewModel(speaker)
        result.imageURL = profileUrl(speaker)
        return result
    }

    private fun profileUrl(speaker: Speaker): String?{
        return profileImageService.url(speaker.id!!)
    }
}