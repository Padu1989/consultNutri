package br.com.paulomoreira.consult.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import br.com.paulomoreira.consult.models.Consulta;
import br.com.paulomoreira.consult.models.Paciente;

public class PacienteDto{
	

	private Long id;
	@NotNull
	private String nome;
	@CPF
	private String cpf;
	private Double idade;
	private Double altura;
	private Double peso;
	private String endereco;
	private List<Consulta> consultas; 
	
	
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setIdade(Double idade) {
		this.idade = idade;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public PacienteDto() {
	}
	
	public PacienteDto(Paciente paciente) {
	}

	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
	}
	public Double getIdade() {
		return idade;
	}
	public Double getAltura() {
		return altura;
	}
	public Double getPeso() {
		return peso;
	}
	public String getEndereco() {
		return endereco;
	}
	public Paciente converter(PacienteDto pacienteDto) {
		return new Paciente(nome, cpf, idade, altura, peso, endereco, consultas);
	}

	public static List<PacienteDto> converterLista(List<Paciente> pacientes) {
		return pacientes.stream().map(PacienteDto::new).collect(Collectors.toList());
	}


}
