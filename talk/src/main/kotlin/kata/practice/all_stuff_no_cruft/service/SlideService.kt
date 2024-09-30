package kata.practice.all_stuff_no_cruft.service

import kata.practice.all_stuff_no_cruft.model.Slide
import org.springframework.web.multipart.MultipartFile

interface SlideService {
    fun save(talkId: Long, slide: MultipartFile)
    fun delete(slideId: Long): Boolean

    fun slide(talkId: Long): List<Slide>
    fun url(slideId: Long): String?
}