package kata.practice.all_stuff_no_cruft.application


import kata.practice.all_stuff_no_cruft.application.command.SignupSpeaker
import kata.practice.all_stuff_no_cruft.application.command.UpdateProfileImage
import kata.practice.all_stuff_no_cruft.codable.PasswordEncodable
import kata.practice.all_stuff_no_cruft.common.exception.EntityNotFoundException
import kata.practice.all_stuff_no_cruft.model.Email
import kata.practice.all_stuff_no_cruft.model.EncodedPassword
import kata.practice.all_stuff_no_cruft.model.Profile
import kata.practice.all_stuff_no_cruft.model.Speaker
import kata.practice.all_stuff_no_cruft.repository.SpeakerRepository
import kata.practice.all_stuff_no_cruft.service.ProfileImageService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull


@Service
class SpeakerApplication(
    private val speakerRepository: SpeakerRepository,
    private val passwordEncodable: PasswordEncodable,
    private val profileImageService: ProfileImageService,
){

    @Transactional
    fun handle(command: SignupSpeaker) {
        val email =  Email(command.email)
        val encodedPassword = EncodedPassword(passwordEncodable.encode(command.password))
        val profile = Profile(command.name, command.introduce)

        val speaker = Speaker(
            email =  email,
            encodedPassword = encodedPassword,
            profile =  profile
        )
        speakerRepository.save(speaker)
    }

    @Transactional
    fun handle(command: UpdateProfileImage){
        val id = command.principal.name.toLong()
        val speaker = speakerRepository.findById(id)
            .getOrNull() ?: throw EntityNotFoundException()


        val speakerId = speaker.id!!
        val image = command.image
        val profileImage = profileImageService.profileImage(speakerId)

        if (profileImage != null){
            profileImageService.delete(speakerId)
            profileImageService.save(speakerId, image)
        }else{
            profileImageService.save(speakerId, image)
        }
    }


}