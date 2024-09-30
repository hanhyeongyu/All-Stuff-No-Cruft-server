package kata.practice.all_stuff_no_cruft.codable

interface PasswordEncodable{
    fun encode(password: String): String
    fun matches(rawPassword: String, encodedPassword: String): Boolean
}