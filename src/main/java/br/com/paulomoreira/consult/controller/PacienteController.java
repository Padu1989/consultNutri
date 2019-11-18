package br.com.paulomoreira.consult.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulomoreira.consult.controller.dto.PacienteDto;
import br.com.paulomoreira.consult.models.Paciente;
import br.com.paulomoreira.consult.service.PacienteService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

	@Autowired
	PacienteService pacienteService;

	@PostMapping
	public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody @Valid PacienteDto pacienteDto) {
		Paciente paciente = pacienteService.cadastarPaciente(pacienteDto);
		return ResponseEntity.created(null).body(paciente);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Paciente> detalharPaciente(@PathVariable Long id) throws NotFoundException {
		Paciente paciente = pacienteService.detalharPaciente(id);
		if (paciente == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(paciente);
	}

	@GetMapping
	public Page<Paciente> buscarPacientes(Pageable paginacao) {
		return pacienteService.buscarPacientes(paginacao);
	}

	@PatchMapping("{id}")
	public ResponseEntity<Paciente> atualizarParcialPaciente(@RequestBody @Valid PacienteDto pacienteDto,
			@PathVariable Long id) throws NotFoundException {
		return pacienteService.atualizarParcialPaciente(id, pacienteDto);
	}

	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<Paciente> atualizarPaciente(@PathVariable Long id, @RequestBody PacienteDto pacienteDto)
			throws NotFoundException {
		return pacienteService.atualizarPaciente(id, pacienteDto);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Paciente> deletarPaciente(@PathVariable Long id) throws NotFoundException {
		return pacienteService.deletarPaciente(id);

	}
}