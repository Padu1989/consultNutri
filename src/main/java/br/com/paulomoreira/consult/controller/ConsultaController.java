package br.com.paulomoreira.consult.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

import br.com.paulomoreira.consult.controller.dto.ConsultaDto;
import br.com.paulomoreira.consult.controller.dto.Resposta;
import br.com.paulomoreira.consult.models.Consulta;
import br.com.paulomoreira.consult.service.ConsultaService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/paciente")
public class ConsultaController {

	@Autowired
	ConsultaService consultaService;
	Resposta<ConsultaDto> resposta = new Resposta<>();

	@PostMapping("/{id}/consulta")
	public ResponseEntity<Resposta<ConsultaDto>> cadastrarConsulta(@PathVariable Long id,
			@RequestBody @Valid ConsultaDto consultaDto) throws NotFoundException{
		consultaService.cadastrarConsulta(consultaDto, id);
		resposta.setStatusCode(HttpStatus.CREATED).setMensagem("Consulta adicionada com sucesso.")
		.setResultado(consultaDto);
		return ResponseEntity.created(null).body(resposta);
	}
	
	@GetMapping("/{id}/consulta")
	public ResponseEntity<Resposta<List<Consulta>>> detalharPaciente(@PathVariable Long id) throws NotFoundException {
		Resposta<List<Consulta>> resposta = new Resposta<>();
		List<Consulta> consultas = consultaService.detalharConsultasPorPaciente(id);
			resposta.setStatusCode(HttpStatus.OK).setMensagem("Consulta realizada com sucesso!")
					.setResultado(consultas);
			return ResponseEntity.ok(resposta);
		}
	@GetMapping("/consulta")
	public ResponseEntity<Resposta<Page<ConsultaDto>>> detalharConsulta(Pageable paginacao){
		Page<ConsultaDto> consultaDto = consultaService.detalharTodasConsultas(paginacao);
		Resposta<Page<ConsultaDto>> resposta = new Resposta<>();
		resposta.setStatusCode(HttpStatus.OK).setMensagem("Consulta realizada com sucesso!").setResultado(consultaDto);
		return ResponseEntity.ok(resposta);
		
	}
	
	@PutMapping("/consulta/{id}")
	@Transactional
	public ResponseEntity<Resposta<Consulta>> atualizarConsulta(@PathVariable Long id,
			@RequestBody @Valid Consulta consultaAtualizada) throws NotFoundException {
		Resposta<Consulta> resposta = new Resposta<>();
		try {
		Consulta consulta = consultaService.atualizarConsulta(id, consultaAtualizada);
		resposta.setStatusCode(HttpStatus.OK).setMensagem("Consulta atualizada com sucesso.").setResultado(consulta);
		return ResponseEntity.ok(resposta);
		}catch (NullPointerException e) {
			throw new NullPointerException("O Id pesquisado não foi encontrado.");
		}
	}

	@PatchMapping("/consulta/{id}")
	@Transactional
	public ResponseEntity<Resposta<Consulta>> atualizarParcialConsulta(@PathVariable Long id,
			@RequestBody @Valid Consulta consultaAtualizada) throws NotFoundException {
		try {
		Resposta<Consulta> resposta = new Resposta<>();
		Consulta consulta = consultaService.atualizarParcialConsulta(id, consultaAtualizada);
		resposta.setStatusCode(HttpStatus.OK).setResultado(consulta).setMensagem("Consulta atualizada com sucesso.");
		return ResponseEntity.ok(resposta);
		}catch (NullPointerException e) {
			throw new NullPointerException("O Id pesquisado não foi encontrado.");
		}
	}

	@DeleteMapping("/consulta/{id}")
	public ResponseEntity<String> deletarConsulta(@PathVariable Long id) {
		try {
		String mensagem = consultaService.deletarConsulta(id);
		return ResponseEntity.ok(mensagem);
		}catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException("O Id pesquisado não foi encontrado.",0);
		}
	}
}
