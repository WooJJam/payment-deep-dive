package co.kr.woojjam.payment_deep_dive.global.config;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {

	private final SecretKey key;
	private final long accessTokenExpiry;
	private final long refreshTokenExpiry;

	public JwtProvider(
		@Value("${jwt.secret}") String secret,
		@Value("${jwt.access-token-expiry}") long accessTokenExpiry,
		@Value("${jwt.refresh-token-expiry}") long refreshTokenExpiry
	) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
		this.accessTokenExpiry = accessTokenExpiry;
		this.refreshTokenExpiry = refreshTokenExpiry;
	}

	public String generateAccessToken(Long userId) {
		return buildToken(userId, accessTokenExpiry);
	}

	public String generateRefreshToken(Long userId) {
		return buildToken(userId, refreshTokenExpiry);
	}

	public Long getUserId(String token) {
		return Long.parseLong(parseClaims(token).getSubject());
	}

	public boolean isValid(String token) {
		try {
			parseClaims(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	public long getRefreshTokenExpiry() {
		return refreshTokenExpiry;
	}

	private String buildToken(Long userId, long expiryMs) {
		Date now = new Date();
		return Jwts.builder()
			.subject(userId.toString())
			.issuedAt(now)
			.expiration(new Date(now.getTime() + expiryMs))
			.signWith(key)
			.compact();
	}

	private Claims parseClaims(String token) {
		return Jwts.parser()
			.verifyWith(key)
			.build()
			.parseSignedClaims(token)
			.getPayload();
	}
}
