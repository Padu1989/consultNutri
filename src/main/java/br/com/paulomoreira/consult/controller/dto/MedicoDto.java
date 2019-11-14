package br.com.paulomoreira.consult.controller.dto;

import br.com.paulomoreira.consult.models.Medico;

public class MedicoDto {

	private Long id;
	private String nome;
	private String registro;
	private String especialidade;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	public void converterMedico(Medico medico) {
		this.nome = medico.getNome();
		this.registro = medico.getRegistro();
		this.especialidade = medico.getEspecialidade();
		
	}
	
	
}
