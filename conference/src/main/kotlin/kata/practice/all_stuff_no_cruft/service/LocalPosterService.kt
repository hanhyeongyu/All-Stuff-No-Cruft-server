package kata.practice.all_stuff_no_cruft.service

import jakarta.annotation.PostConstruct
import kata.practice.all_stuff_no_cruft.connector.FileConnector
import kata.practice.all_stuff_no_cruft.model.FileUtils
import kata.practice.all_stuff_no_cruft.model.Poster
import kata.practice.all_stuff_no_cruft.repository.PosterRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.Path
import kotlin.jvm.optionals.getOrNull


@Service
class LocalPosterService(
    @Value("\${file.images.poster}")
    private val dir: String,
    private val fileConnector: FileConnector,
    private val posterRepository: PosterRepository,
): PosterService{


    @PostConstruct
    private fun init(){
        val path = Path(dir)
        Files.createDirectories(path)
    }

    override fun save(conferenceId: Long, image: MultipartFile) {
        val fileName = FileUtils.createName(image)
        val path = Paths.get(dir, fileName)
        fileConnector.save(image, path)

        val poster = Poster(
            conferenceId = conferenceId,
            filePath = path.toString()
        )
        posterRepository.save(poster)
    }

    override fun delete(conferenceId: Long): Boolean {
        val poster = posterRepository.findByConferenceId(conferenceId)
            .getOrNull() ?: return false
        posterRepository.delete(poster)

        val path = Paths.get(poster.filePath)
        return fileConnector.delete(path)
    }

    override fun poster(conferenceId: Long): Poster? {
        return posterRepository.findByConferenceId(conferenceId)
            .getOrNull()
    }

    override fun url(conferenceId: Long): String?{
        val poster = posterRepository.findByConferenceId(conferenceId)
            .getOrNull() ?: return null

        val path = Paths.get(poster.filePath)
        val result = fileConnector.url(path)
        return result
    }
}