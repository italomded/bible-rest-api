package br.com.italomded.bible.config.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationErrorDTO {

	String label;
	String message;
	
}
