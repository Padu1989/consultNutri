package br.com.paulomoreira.consult.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.paulomoreira.consult.models.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}
