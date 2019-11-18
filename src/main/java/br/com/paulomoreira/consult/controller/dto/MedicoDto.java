package br.com.paulomoreira.consult.controller.dto;

import org.springframework.data.domain.Page;

import br.com.paulomoreira.consult.models.Medico;

public class MedicoDto {

	private Long id;
	private String nome;
	private String registro;
	private String especialidade;

	public MedicoDto(Medico medico) {
		this.id = medico.getId();
		this.nome = medico.getNome();
		this.registro = medico.getRegistro();
		this.especialidade = medico.getEspecialidade();
	}

	public MedicoDto() {
	}

	public void converterMedico(Medico medico) {
		this.nome = medico.getNome();
		this.registro = medico.getRegistro();
		this.especialidade = medico.getEspecialidade();

	}

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

	public static Page<MedicoDto> converter(Page<Medico> medicos) {
			
		return medicos.map(MedicoDto::new);
		
	}

}
