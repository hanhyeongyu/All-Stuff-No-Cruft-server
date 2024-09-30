package kata.practice.all_stuff_no_cruft.application.command

import org.springframework.web.multipart.MultipartFile

data class AddMedia(
    val talkId: Long,
    val video: MultipartFile
)