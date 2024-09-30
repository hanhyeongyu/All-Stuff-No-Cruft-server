package kata.practice.all_stuff_no_cruft.common.exception

import kata.practice.all_stuff_no_cruft.common.exception.ErrorCode.COMMON_ILLEGAL_STATUS


class IllegalStatusException : BaseException {
    constructor() : super(COMMON_ILLEGAL_STATUS)

    constructor(message: String) : super(message, COMMON_ILLEGAL_STATUS)
}
