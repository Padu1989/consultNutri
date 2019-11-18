package br.com.paulomoreira.consult.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.paulomoreira.consult.controller.dto.MedicoDto;
import br.com.paulomoreira.consult.controller.dto.Resposta;
import br.com.paulomoreira.consult.service.MedicoService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/medico")
public class MedicoController {

	@Autowired
	MedicoService medicoService;

	Resposta<MedicoDto> resposta = new Resposta<>();

	@PostMapping
	public ResponseEntity<Resposta<MedicoDto>> cadastrarMedico(@RequestBody @Valid MedicoDto medicoDto) {

		medicoService.cadastrarMedico(medicoDto);
		resposta.setMensagem("Médico cadastrado com sucesso.").setStatusCode(HttpStatus.CREATED)
				.setResultado(medicoDto);
		return ResponseEntity.created(null).body(resposta);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Resposta<MedicoDto>> buscarMedico(@PathVariable Long id) throws NotFoundException {
		MedicoDto medicoDto = medicoService.detalharMedico(id);
		resposta.setMensagem("Médico detalhado com sucesso.").setStatusCode(HttpStatus.OK).setResultado(medicoDto);
		return ResponseEntity.ok(resposta);
	}
	
	@GetMapping
	public ResponseEntity<Resposta<Page<MedicoDto>>> buscarMedicos(Pageable paginacao){
		Page<MedicoDto> medicoDto = medicoService.buscarMedicos(paginacao);
		Resposta<Page<MedicoDto>> resposta = new Resposta<>();
		resposta.setStatusCode(HttpStatus.OK).setMensagem("Lista de médico detalhada com sucesso.").setResultado(medicoDto);
		return ResponseEntity.ok(resposta);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Resposta<MedicoDto>> atualizarMedcio(@PathVariable Long id,
			@RequestBody @Valid MedicoDto medicoAtualizado) throws NotFoundException {
		MedicoDto medicoDto = medicoService.atualizarMedico(id, medicoAtualizado);
		Resposta<MedicoDto> resposta = new Resposta<>();
		resposta.setStatusCode(HttpStatus.OK).setResultado(medicoDto)
				.setMensagem("Médico " + id + " foi atualizado com sucesso.");
		return ResponseEntity.ok(resposta);
	}

	@PatchMapping("/{id}")
	@Transactional
	public ResponseEntity<Resposta<MedicoDto>> atualizarParcialMedico(@PathVariable Long id,
			@RequestBody @Valid MedicoDto medicoParcialAtualizado) throws NotFoundException {
		MedicoDto medicoDto = medicoService.atualizarParcialMedico(id, medicoParcialAtualizado);
		resposta.setMensagem("Médico " + id + " foi atualizado com sucesso.").setStatusCode(HttpStatus.OK)
				.setResultado(medicoDto);
		return ResponseEntity.ok(resposta);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Resposta<MedicoDto>> deletarMedico(@PathVariable Long id) throws NotFoundException {
		MedicoDto medicoDto = medicoService.deletarMedico(id);
		resposta.setMensagem("Médico " + id + " deletado com sucesso").setStatusCode(HttpStatus.OK)
				.setResultado(medicoDto);
		return ResponseEntity.ok(resposta);
	}
}
