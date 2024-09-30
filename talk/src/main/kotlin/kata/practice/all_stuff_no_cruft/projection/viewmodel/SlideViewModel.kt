package kata.practice.all_stuff_no_cruft.projection.viewmodel

import kata.practice.all_stuff_no_cruft.model.Slide

data class SlideViewModel(
    val id: Long,
    val name: String?,
    var slideURL: String?
){
    constructor(slide: Slide): this(
        slide.id!!,
        slide.name,
        null
    )
}