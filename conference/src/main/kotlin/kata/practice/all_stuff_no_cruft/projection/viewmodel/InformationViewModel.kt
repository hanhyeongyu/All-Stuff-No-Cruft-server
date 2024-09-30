package kata.practice.all_stuff_no_cruft.projection.viewmodel

import kata.practice.all_stuff_no_cruft.model.Information

data class InformationViewModel(
    val content: String,
    val contentType: String
){
    constructor(information: Information): this(
        content = information.content,
        contentType = information.contentType
    )

}