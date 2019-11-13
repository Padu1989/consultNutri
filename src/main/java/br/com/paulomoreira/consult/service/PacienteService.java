package br.com.paulomoreira.consult.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.paulomoreira.consult.controller.dto.PacienteDto;
import br.com.paulomoreira.consult.models.Paciente;
import br.com.paulomoreira.consult.repository.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	PacienteRepository pacienteRepository;

	public Paciente cadastarPaciente(PacienteDto pacienteDto) {
		Paciente paciente = pacienteDto.converter(pacienteDto);
		pacienteRepository.save(paciente);
		System.out.println(
				"O Paciente de nome: " + paciente.getNome() + " e Id: " + paciente.getId() + " foi adicionado.");
		return paciente;

	}

	public List<Paciente> buscarPacientes() {
		return pacienteRepository.findAll();
	}

	public Paciente detalharPaciente(Long id) {
		Paciente paciente = pacienteRepository.findById(id).orElse(null);
			System.out.println("O Paciente " + id + " foi listado.");
			return paciente;
		}


	public ResponseEntity<Paciente> deletarPaciente(Long id) {
		Paciente paciente = pacienteRepository.findById(id).orElse(null);
		if (paciente == null) {
			return ResponseEntity.notFound().build();
		} else {
			pacienteRepository.deleteById(id);
			System.out.println("O Paciente " + id + " deletado");
			return ResponseEntity.noContent().build();
		}
	}

	public ResponseEntity<Paciente> atualizarPaciente(Long id, PacienteDto pacienteDto) {
		Paciente paciente = pacienteRepository.findById(id).orElse(null);
		paciente.setNome(pacienteDto.getNome());
		paciente.setCpf(pacienteDto.getCpf());
		paciente.setIdade(pacienteDto.getIdade());
		paciente.setPeso(pacienteDto.getPeso());
		paciente.setAltura(pacienteDto.getAltura());
		paciente.setEndereco(pacienteDto.getEndereco());
		return ResponseEntity.ok(paciente);

	}

	public ResponseEntity<Paciente> atualizarParcialPaciente(Long id, PacienteDto pacienteDto) {
		Paciente paciente = pacienteRepository.findById(id).orElse(null);
		if(pacienteDto.getNome() == null) {} else {paciente.setNome(pacienteDto.getNome());};
 		if(pacienteDto.getCpf() == null) {} else {paciente.setCpf(pacienteDto.getCpf());};
 		if(pacienteDto.getIdade() == null) {} else {paciente.setIdade(pacienteDto.getIdade());};
 		if(pacienteDto.getPeso() == null) {} else {paciente.setPeso(pacienteDto.getPeso());};
 		if(pacienteDto.getAltura() == null) {} else {paciente.setAltura(paciente.getAltura());};
 		if(pacienteDto.getEndereco() == null) {} else {paciente.setEndereco(paciente.getEndereco());};
 		
 		return ResponseEntity.ok(paciente);
	}
}
