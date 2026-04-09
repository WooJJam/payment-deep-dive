package co.kr.woojjam.payment_deep_dive.global.auth;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import co.kr.woojjam.payment_deep_dive.global.exception.BusinessException;
import co.kr.woojjam.payment_deep_dive.global.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(LoginUser.class)
			&& parameter.getParameterType().equals(Long.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		Long userId = (Long) request.getAttribute(AuthInterceptor.USER_ID_ATTRIBUTE);

		if (userId == null) {
			throw new BusinessException(ErrorCode.INVALID_TOKEN);
		}

		return userId;
	}
}
