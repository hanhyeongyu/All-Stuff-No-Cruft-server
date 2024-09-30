package kata.practice.all_stuff_no_cruft.application

import kata.practice.all_stuff_no_cruft.application.command.Signup
import kata.practice.all_stuff_no_cruft.codable.PasswordEncodable
import kata.practice.all_stuff_no_cruft.model.Email
import kata.practice.all_stuff_no_cruft.model.EncodedPassword
import kata.practice.all_stuff_no_cruft.model.User
import kata.practice.all_stuff_no_cruft.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserApplication(
    private val userRepository: UserRepository,
    private val passwordEncodable: PasswordEncodable,
){

    fun handle(command: Signup) {
        val email =  Email(command.email)
        val encodedPassword = EncodedPassword(passwordEncodable.encode(command.password))

        val user = User(
            email =  email,
            encodedPassword = encodedPassword
        )
        userRepository.save(user)
    }
}