package br.com.italomded.bible.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ResponseDTO<T> {

	@NonNull
	T content;
	final String credits = "API construída por Ítalo Martins de Deus (https://github.com/italomded).";
	final String translation = "Todas as Escrituras em português citadas são da Bíblia Livre "
			+ "(BLIVRE), Copyright © Diego Santos, Mario Sérgio, e Marco Teles, "
			+ "http://sites.google.com/site/biblialivre/ - fevereiro de 2018. Licença "
			+ "Creative Commons Atribuição 3.0 Brasil (http://creativecommons.org/licenses/by/3.0/br/). "
			+ "Reprodução permitida desde que devidamente mencionados fonte e autores.";
	
}
