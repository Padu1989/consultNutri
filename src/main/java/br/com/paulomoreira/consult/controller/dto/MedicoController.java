package br.com.paulomoreira.consult.controller.dto;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulomoreira.consult.models.Medico;
import br.com.paulomoreira.consult.service.MedicoService;

@RestController
@RequestMapping("/medico")
public class MedicoController {

	@Autowired
	MedicoService medicoService;
	
	@PostMapping
	public ResponseEntity<Medico> cadastarMedico (@RequestBody @Valid Medico medico){
		return medicoService.cadastrarMedico(medico);
		
	}
	@GetMapping
	public ResponseEntity<List<Medico>> buscarMedicos (){
		return medicoService.buscarMedicos();	
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Medico> atualizarMedico(@PathVariable Long id, @RequestBody @Valid Medico medicoAtualizado){
		return medicoService.atualizarMedico(id, medicoAtualizado);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarMedico(@PathVariable Long id){
		return medicoService.deletarMedico(id);
	}
	
}
