package br.com.paulomoreira.consult.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.paulomoreira.consult.models.Medico;
import br.com.paulomoreira.consult.repository.MedicoRepository;

@Service
public class MedicoService {
	
	@Autowired
	MedicoRepository medicoRepository;

	public ResponseEntity<Medico> cadastrarMedico(Medico medico) {
		medicoRepository.save(medico);
		return ResponseEntity.created(null).body(medico);
	}

	public ResponseEntity<List<Medico>> buscarMedicos() {
		return ResponseEntity.ok(medicoRepository.findAll());
	}

	public ResponseEntity<Medico> atualizarMedico(Long id, Medico medicoAtualizado) {
		Medico medico = medicoRepository.findById(id).orElse(null);
		medico.setNome(medicoAtualizado.getNome());
		medico.setRegistro(medicoAtualizado.getRegistro());
		medico.setEspecialidade(medicoAtualizado.getEspecialidade());
		return ResponseEntity.ok(medico);
	}

	public ResponseEntity<String> deletarMedico(Long id) {
		medicoRepository.deleteById(id);
		return ResponseEntity.ok("O Medico de Id " + id + " foi deletado com sucesso.");
	}
	
	
	
}
