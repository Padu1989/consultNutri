package br.com.paulomoreira.consult.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.paulomoreira.consult.models.UsuarioAutenticavel;
import br.com.paulomoreira.consult.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioRepository UsuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UsuarioAutenticavel> usuario = UsuarioRepository.findByEmail(username);
		if (usuario.isPresent()) {
			return usuario.get();
		} else {
			throw new UsernameNotFoundException("Dados inválidos.");
		}
	}
}
