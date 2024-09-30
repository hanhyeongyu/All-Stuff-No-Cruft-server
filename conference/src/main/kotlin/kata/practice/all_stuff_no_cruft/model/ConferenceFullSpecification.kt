package kata.practice.all_stuff_no_cruft.model

import kata.practice.all_stuff_no_cruft.Specification

class ConferenceFullSpecification: Specification<Conference>() {
    override fun isSatisfiedBy(candidate: Conference): Boolean {
        val capacity = candidate.capacity
        if (capacity.availableCapacity == 0){
            return true
        }else{
            return false
        }
    }
}


