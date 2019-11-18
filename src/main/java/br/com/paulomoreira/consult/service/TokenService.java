package br.com.paulomoreira.consult.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.paulomoreira.consult.models.UsuarioAutenticavel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	
	@Value("${consultNutricao.jwt.expiration}")
	private String expiration;
	
	@Value("${consultNutricao.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {
		UsuarioAutenticavel logado = (UsuarioAutenticavel) authentication.getPrincipal();
		Date hoje = new Date();
		System.out.println(expiration);
		System.out.println(secret);
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		return Jwts.builder() 
				.setIssuer("API do ConsultNutricao")
				.setSubject(logado.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

}
