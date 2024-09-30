package kata.practice.all_stuff_no_cruft.projection.viewmodel

import kata.practice.all_stuff_no_cruft.model.Price

data class PriceViewModel(
    val amount: Long,
    val currency: String
){
    constructor(price: Price) : this(
        price.amount,
        price.currency
    )
}