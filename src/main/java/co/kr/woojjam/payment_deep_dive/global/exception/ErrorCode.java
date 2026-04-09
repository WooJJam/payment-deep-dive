package co.kr.woojjam.payment_deep_dive.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Common
    INVALID_INPUT(BAD_REQUEST, "C-001", "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C-002", "서버 오류가 발생했습니다."),

    // Order
    INVALID_ORDER(BAD_REQUEST, "O-001", "유효하지 않거나 만료된 주문번호입니다."),
    AMOUNT_MISMATCH(BAD_REQUEST, "O-002", "결제 금액이 주문 금액과 일치하지 않습니다."),

    // Payment
    PAYMENT_GATEWAY_ERROR(BAD_GATEWAY, "P-001", "결제 게이트웨이 오류가 발생했습니다."),
    DUPLICATE_PAYMENT(CONFLICT, "P-002", "이미 처리된 결제 요청입니다."),

    // User
    USER_NOT_FOUND(NOT_FOUND, "U-001", "존재하지 않는 사용자입니다."),
    EMAIL_DUPLICATED(CONFLICT, "U-002", "이미 사용 중인 이메일입니다."),

    // Match
    MATCH_NOT_FOUND(NOT_FOUND, "M-001", "존재하지 않는 매치입니다."),
    MATCH_CLOSED(BAD_REQUEST, "M-002", "마감된 매치입니다."),
    MATCH_SEAT_UNAVAILABLE(CONFLICT, "M-003", "잔여 좌석이 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
