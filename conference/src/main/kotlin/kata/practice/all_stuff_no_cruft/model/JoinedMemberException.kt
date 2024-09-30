package kata.practice.all_stuff_no_cruft.model

import kata.practice.all_stuff_no_cruft.common.exception.BaseException
import kata.practice.all_stuff_no_cruft.common.exception.ErrorCode.COMMON_ILLEGAL_STATUS

class JoinedMemberException: BaseException("User already joined conference", COMMON_ILLEGAL_STATUS)