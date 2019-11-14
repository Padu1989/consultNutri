package br.com.paulomoreira.consult.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.paulomoreira.consult.controller.dto.MedicoDto;
import br.com.paulomoreira.consult.models.Medico;
import br.com.paulomoreira.consult.repository.MedicoRepository;
import javassist.NotFoundException;

@Service
public class MedicoService {

	@Autowired
	MedicoRepository medicoRepository;
	
	private static String mensagem = "Id not found";

	public MedicoDto cadastrarMedico(MedicoDto medicoDto) {
		Medico medico = new Medico();
		medico.converterMedico(medicoDto);
		medicoRepository.save(medico);
		medicoDto.setId(medico.getId());
		return medicoDto;
	}

	public ResponseEntity<List<Medico>> buscarMedicos() {
		return ResponseEntity.ok(medicoRepository.findAll());
	}
	
	public MedicoDto detalharMedico(Long id) throws NotFoundException{
		Medico medico = medicoRepository.findById(id).orElseThrow(() -> new NotFoundException(mensagem));
		MedicoDto medicoDto = new MedicoDto();
		medicoDto.converterMedico(medico);
		return medicoDto;
	}

	public MedicoDto deletarMedico(Long id) throws NotFoundException {
		Medico medico = medicoRepository.findById(id).orElseThrow(() -> new NotFoundException(mensagem));
		MedicoDto medicoDto = new MedicoDto();
		medicoDto.converterMedico(medico);
		medicoRepository.deleteById(id);
		return medicoDto;
	}

	public MedicoDto atualizarMedico(Long id, MedicoDto medicoAtualizado) throws NotFoundException {
		Medico medico = medicoRepository.findById(id).orElseThrow(() -> new NotFoundException(mensagem));
		MedicoDto medicoDto = medico.converterMedico(medicoAtualizado);
		medicoDto.setId(id);
		
		return medicoDto;
	}

	public MedicoDto atualizarParcialMedico(Long id, @Valid MedicoDto medicoParcialAtualizado) throws NotFoundException {
		Medico medico = medicoRepository.findById(id).orElseThrow(() -> new NotFoundException(mensagem));
		if (medicoParcialAtualizado.getNome() == null) {} else {medico.setNome(medicoParcialAtualizado.getNome());};
		if (medicoParcialAtualizado.getEspecialidade() == null) {} else {medico.setEspecialidade(medicoParcialAtualizado.getEspecialidade());};
		if (medicoParcialAtualizado.getRegistro() == null) {} else {medico.setRegistro(medicoParcialAtualizado.getRegistro());};
		MedicoDto medicoDto = new MedicoDto();
		medicoDto.setId(id);
		medicoDto.converterMedico(medico);
		return medicoDto;
		
	}
	}
