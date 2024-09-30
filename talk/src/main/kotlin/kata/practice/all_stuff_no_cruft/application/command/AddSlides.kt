package kata.practice.all_stuff_no_cruft.application.command

import org.springframework.web.multipart.MultipartFile

data class AddSlides(
    val talkId: Long,
    val uploaderId: Long,
    var slides: List<MultipartFile>
)