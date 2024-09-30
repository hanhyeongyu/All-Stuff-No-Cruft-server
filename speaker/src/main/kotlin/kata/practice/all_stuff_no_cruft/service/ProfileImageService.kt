package kata.practice.all_stuff_no_cruft.service

import kata.practice.all_stuff_no_cruft.model.ProfileImage
import org.springframework.web.multipart.MultipartFile

interface ProfileImageService {
    fun save(speakerId: Long, image: MultipartFile)
    fun delete(speakerId: Long): Boolean

    fun profileImage(speakerId: Long): ProfileImage?

    fun url(speakerId: Long): String?
}