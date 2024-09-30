package kata.practice.all_stuff_no_cruft.projection.viewmodel

import kata.practice.all_stuff_no_cruft.model.Address

data class AddressViewModel(
    val city: String,
    val street: String,
    val zipCode: String,
    val coordinate: CoordinateViewModel,
){
    constructor(address: Address) : this(
        city = address.city,
        street = address.street,
        zipCode = address.zipCode,
        coordinate = CoordinateViewModel(address.coordinate)
    )
}

