package br.com.paulomoreira.consult.controller.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Resposta<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7660180848952090005L;

	private HttpStatus statusCode;

	private String mensagem;

	private T resultado;

	public HttpStatus getStatusCode() {
		return statusCode;
	}
	
	public static <T> Resposta<T> getInstance(){
		return new Resposta<T>();
	}

	public Resposta<T> setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
		return this;
	}

	public String getMensagem() {
		return mensagem;
	}

	public Resposta<T> setMensagem(String mensagem) {
		this.mensagem = mensagem;
		return this;
	}

	public T getResultado() {
		return resultado;
	}

	public Resposta<T> setResultado(T resultado) {
		this.resultado = resultado;
		return this;
	}

	public void setResultado(String mensagem) {
		this.mensagem = mensagem;
		
	}

}
