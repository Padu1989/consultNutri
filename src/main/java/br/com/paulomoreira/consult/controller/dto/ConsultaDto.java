package br.com.paulomoreira.consult.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.paulomoreira.consult.models.Consulta;
import br.com.paulomoreira.consult.models.Medico;

public class ConsultaDto {

	private Long id;
	private LocalDateTime dataConsulta;
	private String nomePaciente;
	private String cpfPaciente;
	private Medico medico;
	private String receita;
	private String descricao;
	
	public ConsultaDto() {
	}
	
	public ConsultaDto(Consulta consulta) {
		this.id = consulta.getId();
		this.dataConsulta = consulta.getDataConsulta();
		this.nomePaciente = consulta.getPaciente().getNome();
		this.cpfPaciente = consulta.getPaciente().getCpf();
		this.medico = consulta.getMedico();
		this.receita = consulta.getReceita();
		this.descricao = consulta.getDescricao();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	
	public LocalDateTime getDataConsulta() {
		return dataConsulta;
	}
	public void setDataConsulta(LocalDateTime dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	
	
	public String getCpfPaciente() {
		return cpfPaciente;
	}
	public void setCpfPaciente(String cpfPaciente) {
		this.cpfPaciente = cpfPaciente;
	}
	public Consulta converterConsulta(ConsultaDto consultaDto) {
		return new Consulta(id, nomePaciente, cpfPaciente, medico, receita, descricao, dataConsulta);
	}
	public static Page<ConsultaDto> converter(Page<Consulta> consultas) {
		
		return consultas.map(ConsultaDto::new);
		
	}
	
	
	
}
