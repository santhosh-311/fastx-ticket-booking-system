package com.hexaware.webtoken;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;


@Service
public class JwtService {
	
	private static final String SECRET="76019FFB5046AB360A92D9DFA85F6B905D83807AEE56CA3C20DAD423725378022C8FAEB246E32EB70A38671D5D6DCE059084C1402F3E957DD1E9366C12ACBA57";
	
	private static final long VALIDITY= TimeUnit.MINUTES.toMillis(60);
	
	private static final String getSecretKey() {
		SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);
		String encodedKey=DatatypeConverter.printHexBinary(key.getEncoded());
		return encodedKey;
	}
	
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder()
		.setSubject(userDetails.getUsername())
		.setIssuedAt(Date.from(Instant.now()))
		.setExpiration(Date.from(Instant.now().plusMillis(VALIDITY)))
		.signWith(generatekey())
		.compact();
	}
	
	private SecretKey generatekey() {
//		System.out.println(SECRET);
		byte[] decodedKey = Base64.getDecoder().decode(SECRET);
		return Keys.hmacShaKeyFor(decodedKey);
	}

	public String extractUsername(String jwt) {
		Claims claims=getClaims(jwt);
        return claims.getSubject();
		
	}
	
	private Claims getClaims(String jwt) {
		Claims claims = Jwts.parserBuilder()
                .setSigningKey(generatekey()) // Set the signing key
                .build()
                .parseClaimsJws(jwt) // Parse the JWT
                .getBody(); // Extract the claims
		return claims;
	}

	public boolean isTokenValid(String jwt) {
		Claims claims = getClaims(jwt);
		return claims.getExpiration().after(Date.from(Instant.now()));
	}
	
}