package kata.practice.all_stuff_no_cruft.service

import kata.practice.all_stuff_no_cruft.model.Media
import org.springframework.web.multipart.MultipartFile
import java.util.*

interface MediaService {
    fun save(mediaId: UUID, talkId: Long, video: MultipartFile)
    fun delete(mediaId: UUID): Boolean

    fun medias(talkId: Long): List<Media>
    fun process(mediaId: UUID)

    fun url(mediaId: UUID): String?
}