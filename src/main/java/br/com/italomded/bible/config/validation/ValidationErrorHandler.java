package br.com.italomded.bible.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationErrorHandler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public List<ValidationErrorDTO> handle(MethodArgumentNotValidException exception) {
		List<ValidationErrorDTO> errorList = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(error -> {
			String label = error.getField();
			String message = error.getDefaultMessage();
			errorList.add(new ValidationErrorDTO(label, message));
		});
		return errorList;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = Error.class)
	public ValidationErrorDTO handle(Error error) {
		String message = error.getMessage();
		ValidationErrorDTO errorDTO = new ValidationErrorDTO("file", message);
		return errorDTO;
	}
	
}
