package kata.practice.all_stuff_no_cruft.jwt



interface JwtComposer {
    fun compose(subject: String, roles: List<String>): IssueToken
    fun compose(refreshToken: String): IssueToken
}