package br.com.italomded.bible.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;

import br.com.italomded.bible.model.User;

@Service
public class TokenService {

	@Value("${bible.jwt.expiration}")
	private String expiration;
	
	@Value("${bible.jwt.secret}")
	private String secret;
	
	private String issuer = "Bible API";
	
	public String gerarToken(Authentication authentication) {
		User logged = (User) authentication.getPrincipal();
		Date now = new Date();
		Date expirationDate = new Date(now.getTime() + Long.parseLong(expiration));
		Algorithm algorithm = Algorithm.HMAC256(secret);
		return JWT.create()
				.withIssuer(issuer)
				.withSubject(logged.getUsername())
				.withIssuedAt(now)
				.withExpiresAt(expirationDate)
				.sign(algorithm);			
	}
	
	public Boolean isTokenValid(String token) {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
		try {
			verifier.verify(token);
			return true;
		} catch (JWTVerificationException exception) {
			return false;
		}
	}
	
	public String getUserUsername(String token) {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
		String username = verifier.verify(token).getSubject();
		return username;
	}
}
