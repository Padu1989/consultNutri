package br.com.paulomoreira.consult.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.paulomoreira.consult.models.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{

}
