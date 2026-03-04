package co.kr.woojjam.payment_deep_dive.order.presentation.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.kr.woojjam.payment_deep_dive.order.application.facade.OrderPaymentFacade;
import co.kr.woojjam.payment_deep_dive.order.exception.AmountMismatchException;
import co.kr.woojjam.payment_deep_dive.order.exception.InvalidOrderException;
import co.kr.woojjam.payment_deep_dive.order.presentation.request.CheckoutRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CheckoutController {

	@Value("${payments.client.key}")
	private String clientKey;

	private final OrderPaymentFacade orderPaymentFacade;

	@GetMapping("/checkout")
	public String checkout(Model model) {
		CheckoutRequest checkout = orderPaymentFacade.prepareCheckout();

		model.addAttribute("clientKey", clientKey);
		model.addAttribute("checkout", checkout);
		return "checkout";
	}

	@GetMapping("/success")
	public String success(
		@RequestParam String paymentKey,
		@RequestParam String orderId,
		@RequestParam Long amount,
		Model model
	) {
		try {
			orderPaymentFacade.validatePayment(orderId, amount);
		} catch (InvalidOrderException e) {
			return failWith(model, "INVALID_ORDER_ID", e.getMessage(), orderId);
		} catch (AmountMismatchException e) {
			return failWith(model, "AMOUNT_MISMATCH", e.getMessage(), orderId);
		}

		log.info("결제 검증이 완료되었습니다.");

		model.addAttribute("clientKey", clientKey);
		model.addAttribute("paymentKey", paymentKey);
		model.addAttribute("orderId", orderId);
		model.addAttribute("amount", amount);
		return "success";
	}

	@GetMapping("/fail")
	public String fail(
		@RequestParam String code,
		@RequestParam String message,
		@RequestParam(required = false) String orderId,
		Model model
	) {
		model.addAttribute("code", code);
		model.addAttribute("message", message);
		model.addAttribute("orderId", orderId);
		return "fail";
	}

	private String failWith(final Model model, final String code, final String message, final String orderId) {
		log.info("결제 검증에 실패하였습니다.");
		model.addAttribute("code", code);
		model.addAttribute("message", message);
		model.addAttribute("orderId", orderId);
		return "fail";
	}
}
