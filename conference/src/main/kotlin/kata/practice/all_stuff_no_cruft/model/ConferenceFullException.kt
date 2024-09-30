package kata.practice.all_stuff_no_cruft.model

import kata.practice.all_stuff_no_cruft.common.exception.BaseException
import kata.practice.all_stuff_no_cruft.common.exception.ErrorCode.SOLD_OUT

class ConferenceFullException: BaseException("매진된 티켓입니다.", SOLD_OUT)