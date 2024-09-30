package kata.practice.all_stuff_no_cruft.service

import jakarta.annotation.PostConstruct
import kata.practice.all_stuff_no_cruft.connector.FileConnector
import kata.practice.all_stuff_no_cruft.model.FileUtils
import kata.practice.all_stuff_no_cruft.model.Slide
import kata.practice.all_stuff_no_cruft.repository.SlideRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Paths
import kotlin.io.path.Path
import kotlin.jvm.optionals.getOrNull

@Service
class LocalSlideService(
    @Value("\${file.pdfs.slide}")
    private val dir: String,
    private val fileConnector: FileConnector,
    private val slideRepository: SlideRepository
): SlideService{

    @PostConstruct
    private fun init(){
        fileConnector.createDirectories(dir)
    }

    override fun save(talkId: Long, slide: MultipartFile) {
        val fileName = FileUtils.createName(slide)
        val path = Paths.get(dir, fileName)
        fileConnector.save(slide, path)


        val slide = Slide(
            talkId = talkId,
            name = slide.originalFilename,
            filePath = path.toString()
        )
        slideRepository.save(slide)
    }

    override fun delete(slideId: Long): Boolean {
        val slide = slideRepository.findById(slideId)
            .getOrNull() ?: return false
        slideRepository.delete(slide)

        val path = Paths.get(slide.filePath)
        return fileConnector.delete(path)
    }

    override fun slide(talkId: Long): List<Slide> {
        return slideRepository.findAllByTalkId(talkId)
    }

    override fun url(slideId: Long): String? {
        val slide = slideRepository.findById(slideId)
            .getOrNull() ?: return null
        val path = Paths.get(slide.filePath)
        val result = fileConnector.url(path)
        return result
    }
}