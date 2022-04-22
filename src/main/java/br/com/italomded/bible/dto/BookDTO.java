package br.com.italomded.bible.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.italomded.bible.model.Book;
import lombok.Getter;

@Getter
public class BookDTO {

	private String abbreviation;
	private String brief;
	private List<ChapterDTO> chapters;
	
	public BookDTO(Book book) {
		this.abbreviation = book.getAbbreviation();
		this.brief = book.getBrief();
		this.chapters = book.getChapters().stream().map(ch -> new ChapterDTO(ch)).collect(Collectors.toList());
	}
	
}
