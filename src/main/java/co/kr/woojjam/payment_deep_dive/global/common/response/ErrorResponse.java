package co.kr.woojjam.payment_deep_dive.global.common.response;

import co.kr.woojjam.payment_deep_dive.global.exception.ErrorCode;

public record ErrorResponse(String code, String message) {

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getCode(), errorCode.getMessage());
    }
}
