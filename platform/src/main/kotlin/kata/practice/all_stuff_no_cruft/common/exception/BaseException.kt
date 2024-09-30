package kata.practice.all_stuff_no_cruft.common.exception

open class BaseException : RuntimeException {
    var errorCode: ErrorCode? = null

    constructor()

    constructor(errorCode: ErrorCode) : super(errorCode.errorMessage) {
        this.errorCode = errorCode
    }

    constructor(message: String, errorCode: ErrorCode) : super(message) {
        this.errorCode = errorCode
    }

    constructor(message: String, errorCode: ErrorCode, cause: Throwable) : super(message, cause) {
        this.errorCode = errorCode
    }
}
