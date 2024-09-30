package kata.practice.all_stuff_no_cruft.model

import jakarta.persistence.Embeddable

@Embeddable
data class Price(
    val amount: Long,
    val currency: String
){

    companion object{
        fun won(price: Long): Price{
            return Price(price, "won")
        }

    }

}