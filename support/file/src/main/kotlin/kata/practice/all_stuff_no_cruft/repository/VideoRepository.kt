package kata.practice.all_stuff_no_cruft.repository

import kata.practice.all_stuff_no_cruft.model.Video
import org.springframework.data.jpa.repository.JpaRepository


interface VideoRepository: JpaRepository<Video, Long>