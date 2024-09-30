package kata.practice.all_stuff_no_cruft.service

import kata.practice.all_stuff_no_cruft.model.Video
import org.springframework.web.multipart.MultipartFile

interface VideoService {
    fun save(multipartFile: MultipartFile)

    fun video(id: Long): Video?

    fun process(id: Long)
}