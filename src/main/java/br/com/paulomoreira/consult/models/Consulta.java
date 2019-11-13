package br.com.paulomoreira.consult.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.paulomoreira.consult.controller.dto.ConsultaDto;

@Entity
@Table(name = "CONSULTA")
public class Consulta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private LocalDateTime dataConsulta = LocalDateTime.now();
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name = "medico_id")
	private Medico medico;
	
	private String receita;
	
	private String descricao;
	
	public Consulta(Long id, String nomePaciente, String cpfPaciente, Medico medico, String receita, String descricao, LocalDateTime dataConsulta) {
		this.medico = medico;
		this.receita = receita;
		this.descricao = descricao;
		dataConsulta = this.dataConsulta;
	}
	
	public Consulta() {
	
	}

	public Consulta(Consulta consulta) {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(LocalDateTime dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public String getReceita() {
		return receita;
	}

	public void setReceita(String receita) {
		this.receita = receita;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ConsultaDto converterConsultaDtoParaConsulta(ConsultaDto consultaDto, 
			Paciente paciente, Consulta consulta) {
		consultaDto.setNomePaciente(paciente.getNome());
		consultaDto.setCpfPaciente(paciente.getCpf());
		consultaDto.setReceita(consulta.getReceita());
		consultaDto.setMedico(consulta.getMedico());
		consultaDto.setDataConsulta(consulta.getDataConsulta());
		return consultaDto;
	}

	public Consulta atualizar(Consulta consultasEncontradas) {
		consultasEncontradas.setReceita(receita);
		consultasEncontradas.setDescricao(descricao);
		Consulta consulta = consultasEncontradas;
		return consulta;
	}
}
