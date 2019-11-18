package br.com.paulomoreira.consult.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.paulomoreira.consult.controller.dto.MedicoDto;

@Entity
@Table(name = "MEDICO")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	public Medico() {
	}

	public MedicoDto converterMedico(MedicoDto medicoDto) {
		this.nome = medicoDto.getNome();
		this.especialidade = medicoDto.getEspecialidade();
		this.registro = medicoDto.getRegistro();
		return medicoDto;
	}

}
