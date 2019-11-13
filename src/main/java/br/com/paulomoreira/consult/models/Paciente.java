package br.com.paulomoreira.consult.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "paciente")
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String cpf;
	private Double idade;
	private Double altura;
	private Double peso;
	private String endereco;
	@JsonBackReference
	@OneToMany(mappedBy = "paciente", targetEntity = Consulta.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Consulta> consultas = new ArrayList<>();

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

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

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

	public Paciente(String nome, String cpf, Double idade, Double altura, Double peso, String endereco, List<Consulta> consultas) {
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
		this.altura = altura;
		this.peso = peso;
		this.endereco = endereco;
		this.consultas = consultas;

	}

	public Paciente() {
	}

	public Paciente(Paciente paciente) {
	}

}
