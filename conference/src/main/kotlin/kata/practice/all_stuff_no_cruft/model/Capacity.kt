package kata.practice.all_stuff_no_cruft.model

import jakarta.persistence.Embeddable
import kata.practice.all_stuff_no_cruft.common.exception.BaseException
import kata.practice.all_stuff_no_cruft.common.exception.ErrorCode.SOLD_OUT
import kotlin.math.abs

@Embeddable
data class  Capacity(
    val maxCapacity: Int,
    val availableCapacity: Int,
    val currentCapacity: Int
){


    companion object{
        fun create(totalCapacity: Int): Capacity {
            return Capacity(
                maxCapacity = totalCapacity,
                availableCapacity = totalCapacity,
                currentCapacity = 0
            )
        }
    }

    fun join(): Capacity {
        if (availableCapacity == 0){
            throw ConferenceFullException()
        }

        val result = Capacity(
            maxCapacity = maxCapacity,
            availableCapacity = availableCapacity - 1,
            currentCapacity = currentCapacity + 1
        )
        assert(availableCapacity + currentCapacity == maxCapacity)
        return result
    }

    fun updateMaxCapacity(newMaxCapacity: Int): Capacity{
        val diff = newMaxCapacity - this.maxCapacity

        if (diff == newMaxCapacity){
            return this
        }else if (diff < 0 && availableCapacity < abs(diff)) {
            throw IllegalStateException("Capacity  cannot be updated to $newMaxCapacity)")
        }

        val result = Capacity(
            maxCapacity = this.maxCapacity + diff,
            availableCapacity = availableCapacity + diff,
            currentCapacity = currentCapacity
        )

        assert(this.maxCapacity == newMaxCapacity)
        assert(availableCapacity + currentCapacity == this.maxCapacity)

        return result
    }

}