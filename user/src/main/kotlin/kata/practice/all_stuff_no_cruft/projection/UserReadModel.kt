package kata.practice.all_stuff_no_cruft.projection

import kata.practice.all_stuff_no_cruft.model.User

interface UserReadModel {
    fun user(email: String): User?
}