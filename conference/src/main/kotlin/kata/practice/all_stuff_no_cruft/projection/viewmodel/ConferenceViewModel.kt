package kata.practice.all_stuff_no_cruft.projection.viewmodel

import kata.practice.all_stuff_no_cruft.model.Conference


data class ConferenceViewModel(
    val id: Long,
    val name: String,
    var posterURL: String?,
    val information: InformationViewModel,
    val address: AddressViewModel,
    val schedule: ScheduleViewModel,
    val capacity: CapacityViewModel,
    val price: PriceViewModel
){

    constructor(conference: Conference) : this(
        conference.id!!,
        conference.name,
        null,
        InformationViewModel(conference.information),
        AddressViewModel(conference.address),
        ScheduleViewModel(conference.schedule),
        CapacityViewModel(conference.capacity),
        PriceViewModel(conference.price)
    )
}