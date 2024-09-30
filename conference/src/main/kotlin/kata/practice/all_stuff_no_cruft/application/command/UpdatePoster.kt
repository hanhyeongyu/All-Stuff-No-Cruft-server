package kata.practice.all_stuff_no_cruft.application.command

import org.springframework.web.multipart.MultipartFile

data class UpdatePoster(
    val conferenceId: Long,
    val image: MultipartFile
)