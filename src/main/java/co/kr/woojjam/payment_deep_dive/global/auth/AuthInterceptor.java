package co.kr.woojjam.payment_deep_dive.global.auth;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import co.kr.woojjam.payment_deep_dive.global.config.JwtProvider;
import co.kr.woojjam.payment_deep_dive.global.exception.BusinessException;
import co.kr.woojjam.payment_deep_dive.global.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

	static final String USER_ID_ATTRIBUTE = "userId";
	private static final String BEARER_PREFIX = "Bearer ";

	private final JwtProvider jwtProvider;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (!(handler instanceof HandlerMethod method)) {
			return true;
		}

		boolean requiresAuth = method.hasMethodAnnotation(Auth.class)
			|| method.getBeanType().isAnnotationPresent(Auth.class);

		String token = resolveToken(request);

		if (requiresAuth) {
			if (!StringUtils.hasText(token)) {
				throw new BusinessException(ErrorCode.LOGIN_REQUIRED);
			}
			if (!jwtProvider.isValid(token)) {
				throw new BusinessException(ErrorCode.INVALID_TOKEN);
			}
		}

		if (StringUtils.hasText(token) && jwtProvider.isValid(token)) {
			request.setAttribute(USER_ID_ATTRIBUTE, jwtProvider.getUserId(token));
		}

		return true;
	}

	private String resolveToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if (StringUtils.hasText(header) && header.startsWith(BEARER_PREFIX)) {
			return header.substring(BEARER_PREFIX.length());
		}
		return null;
	}
}
