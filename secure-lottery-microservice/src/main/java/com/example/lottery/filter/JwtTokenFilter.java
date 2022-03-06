package com.example.lottery.filter;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtTokenFilter extends GenericFilterBean {
	private static final String BEARER = "Bearer";

	private String secret;
	private UserDetailsService userDetailsService;

	public JwtTokenFilter(String secret, UserDetailsService userDetailsService) {
		this.secret = secret;
		this.userDetailsService = userDetailsService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.err.println("doFilter(): "+secret);
		String headerValue = ((HttpServletRequest) request).getHeader("Authorization");
		getBearerToken(headerValue).ifPresent(token -> {
			String username = getClaimFromToken(token, Claims::getSubject);
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			if (username.equals(userDetails.getUsername()) && !isJwtExpired(token)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		});

		chain.doFilter(request, response);
	}

	private Optional<String> getBearerToken(String headerVal) {
		if (headerVal != null && headerVal.startsWith(BEARER)) {
			return Optional.of(headerVal.replace(BEARER, "").trim());
		}
		return Optional.empty();
	}

	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claimsResolver.apply(claims);
	}

	private Boolean isJwtExpired(String token) {
		Date expirationDate = getClaimFromToken(token, Claims::getExpiration);
		return expirationDate.before(new Date());
	}
}
