package kata.practice.all_stuff_no_cruft.application

import java.nio.file.Path

interface VideoProcessor {
    fun process(videoPath: Path, outputPath: Path)
}