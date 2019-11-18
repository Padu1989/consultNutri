package br.com.paulomoreira.consult.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.paulomoreira.consult.models.UsuarioAutenticavel;

public interface UsuarioRepository extends JpaRepository<UsuarioAutenticavel, Long>{
	
	Optional<UsuarioAutenticavel> findByEmail(String email);

}
