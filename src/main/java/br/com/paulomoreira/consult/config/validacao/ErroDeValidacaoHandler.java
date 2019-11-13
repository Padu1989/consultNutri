package br.com.paulomoreira.consult.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.paulomoreira.consult.controller.dto.Resposta;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception) {
		List<ErroDeFormularioDto> dto = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(), mensagem);
			dto.add(erro);
		});
		
		return dto;
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Resposta<Object>> vai(NullPointerException ex) {
		Resposta<Object> resposta = new Resposta<>();
		HttpStatus notFound = HttpStatus.resolve(404);
		resposta.setStatusCode(notFound).setMensagem(ex.getMessage()).setResultado(ex.getSuppressed());
		return ResponseEntity.status(notFound).body(resposta);
		
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Resposta<Object>> capturarErro(EmptyResultDataAccessException ex) {
		Resposta<Object> resposta = new Resposta<>();
		HttpStatus notFound = HttpStatus.resolve(404);
		resposta.setStatusCode(notFound).setMensagem(ex.getMessage()).setResultado(ex.getSuppressed());
		return ResponseEntity.status(notFound).body(resposta);
		
	}
}

