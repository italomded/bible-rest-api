package br.com.italomded.bible.config.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

@RestControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(value = MultipartException.class)
	public ResponseEntity<?> handle(MultipartException exception) {
		return ResponseEntity.badRequest().build();
	}
	
}
