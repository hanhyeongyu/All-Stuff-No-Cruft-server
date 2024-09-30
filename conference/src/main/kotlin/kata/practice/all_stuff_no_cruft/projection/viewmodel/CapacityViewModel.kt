package kata.practice.all_stuff_no_cruft.projection.viewmodel

import kata.practice.all_stuff_no_cruft.model.Capacity

data class CapacityViewModel(
    val maxCapacity: Int,
    val availableCapacity: Int,
    val currentCapacity: Int
){
    constructor(capacity: Capacity): this(
        capacity.maxCapacity,
        capacity.availableCapacity,
        capacity.currentCapacity
    )
}