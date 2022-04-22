package br.com.italomded.bible.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.italomded.bible.model.Chapter;
import lombok.Getter;

@Getter
public class ChapterDTO {
	
	private Integer number;
	private List<VerseDTO> verses;
	
	public ChapterDTO(Chapter chapter) {
		this.number = chapter.getNumber();
		this.verses = chapter.getVerses().stream().map(vr -> new VerseDTO(vr)).collect(Collectors.toList());
	}

}
