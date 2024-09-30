package kata.practice.all_stuff_no_cruft.service

import kata.practice.all_stuff_no_cruft.model.Poster
import org.springframework.web.multipart.MultipartFile


interface PosterService{
    fun save(conferenceId: Long, image: MultipartFile)
    fun delete(conferenceId: Long): Boolean

    fun poster(conferenceId: Long): Poster?

    fun url(conferenceId: Long): String?
}