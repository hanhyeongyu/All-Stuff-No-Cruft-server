package kata.practice.all_stuff_no_cruft.common.response

import kata.practice.all_stuff_no_cruft.common.exception.BaseException
import kata.practice.all_stuff_no_cruft.common.exception.ErrorCode



class ResponseException(
    val message: String? = null,
    val errorCode: Int? = null
){

    constructor(exception: BaseException): this(
        message = exception.message,
        errorCode = exception.errorCode?.errorCode
    )

    constructor(errorCode: ErrorCode): this(
        message = errorCode.errorMessage,
        errorCode = errorCode.errorCode
    )
}
