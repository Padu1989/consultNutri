package br.com.paulomoreira.consult.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.paulomoreira.consult.controller.dto.ConsultaDto;
import br.com.paulomoreira.consult.models.Consulta;
import br.com.paulomoreira.consult.models.Paciente;
import br.com.paulomoreira.consult.repository.ConsultaRepository;
import br.com.paulomoreira.consult.repository.PacienteRepository;
import javassist.NotFoundException;

@Service
public class ConsultaService {

	@Autowired
	ConsultaRepository consultaRepository;

	@Autowired
	PacienteRepository pacienteRepository;

	public ConsultaDto cadastrarConsulta(ConsultaDto consultaDto, Long id) throws NotFoundException {
		Consulta consulta = consultaDto.converterConsulta(consultaDto);
		Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Id não encontrado."));
		consulta.setPaciente(paciente);
		paciente.getConsultas().add(consulta);
		consultaRepository.save(consulta);
		consultaDto = consulta.converterConsultaDtoParaConsulta(consultaDto, paciente, consulta);
		consultaDto.setId(consulta.getId());
		return consultaDto;
	}

	public List<Consulta> detalharConsultasPorPaciente(Long id) throws NotFoundException{
	
			Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Id não encontrado"));

			List<Consulta> consultas = paciente.getConsultas();
			return (consultas);
	}

	public Consulta atualizarConsulta(Long id, Consulta consultaAtualizada) {
		Consulta consultaEncontrada = consultaRepository.findById(id).orElse(null);
		Consulta consulta = consultaAtualizada.atualizar(consultaEncontrada);
		return (consulta);
	}

	public Consulta atualizarParcialConsulta(Long id, @Valid Consulta consultaAtualizada) {
		Consulta consulta = consultaRepository.findById(id).orElse(null);
		consulta.setMedico(consultaAtualizada.getMedico());
		consulta.setReceita(consultaAtualizada.getReceita());
		consulta.setDescricao(consultaAtualizada.getDescricao());
		return consulta;
	}

	public String deletarConsulta(Long id) {
		consultaRepository.deleteById(id);
		String mensagem = "Consulta " + id + " deletada com sucesso.";
		return mensagem;
	}
}
