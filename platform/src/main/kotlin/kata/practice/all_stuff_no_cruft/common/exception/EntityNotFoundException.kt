package kata.practice.all_stuff_no_cruft.common.exception

import kata.practice.all_stuff_no_cruft.common.exception.ErrorCode.COMMON_ENTITY_NOT_FOUND

class EntityNotFoundException : BaseException {
    constructor() : super(COMMON_ENTITY_NOT_FOUND)

    constructor(message: String) : super(message, COMMON_ENTITY_NOT_FOUND)
}
