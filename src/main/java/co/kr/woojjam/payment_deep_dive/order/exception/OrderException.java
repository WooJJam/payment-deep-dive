package co.kr.woojjam.payment_deep_dive.order.exception;

import co.kr.woojjam.payment_deep_dive.global.exception.BusinessException;
import co.kr.woojjam.payment_deep_dive.global.exception.ErrorCode;

public class OrderException extends BusinessException {

    public OrderException(ErrorCode errorCode) {
        super(errorCode);
    }
}
