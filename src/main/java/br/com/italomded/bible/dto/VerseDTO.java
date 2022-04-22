package br.com.italomded.bible.dto;

import br.com.italomded.bible.model.Verse;
import lombok.Getter;

@Getter
public class VerseDTO {

	private Integer number;
	private String text;
	
	public VerseDTO(Verse verse) {
		this.number = verse.getNumber();
		this.text = verse.getText();
	}
	
}
