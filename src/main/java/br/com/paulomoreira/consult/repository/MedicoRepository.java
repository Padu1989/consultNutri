package br.com.paulomoreira.consult.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.paulomoreira.consult.models.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
